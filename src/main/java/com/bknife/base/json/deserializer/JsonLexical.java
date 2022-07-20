package com.bknife.base.json.deserializer;

import java.io.IOException;
import java.io.Reader;

import com.bknife.base.json.deserializer.JsonToken.Type;
import com.bknife.base.util.Chars;

public class JsonLexical {
    private Reader reader;
    private StringBuffer buffer = new StringBuffer();

    private int linenum;
    private int offset;

    private int curCh;

    public JsonLexical(Reader reader) throws IOException {
        this.reader = reader;
        curCh = reader.read();
    }

    private void skipSpace() throws IOException {
        while (Chars.isSpace((char) curCh))
            next();
    }

    private boolean isEof() {
        return curCh == -1;
    }

    private String takeBuffer() {
        String str = buffer.toString();
        buffer.delete(0, buffer.length());
        return str;
    }

    private char next() throws IOException {
        char ch = (char) curCh;
        if (ch == '\n') {
            ++linenum;
            offset = 0;
        } else
            ++offset;
        curCh = reader.read();
        return ch;
    }

    public JsonToken nextToken() throws IOException {
        // 跳过空白字符
        skipSpace();
        if (isEof())
            return new JsonToken(JsonToken.Type.Eof, takeBuffer(), linenum, offset);
        char ch = next();
        switch (ch) {
            case '{':
                return new JsonToken(JsonToken.Type.ObjectOpen, takeBuffer(), linenum, offset);
            case '}':
                return new JsonToken(JsonToken.Type.ObjectClose, takeBuffer(), linenum, offset);
            case '[':
                return new JsonToken(JsonToken.Type.ArrayOpen, takeBuffer(), linenum, offset);
            case ']':
                return new JsonToken(JsonToken.Type.ArrayClose, takeBuffer(), linenum, offset);
            case ',':
                return new JsonToken(JsonToken.Type.Comma, takeBuffer(), linenum, offset);
            case ':':
                return new JsonToken(JsonToken.Type.Colon, takeBuffer(), linenum, offset);
            case '"':
                return parseString();
            case 'n':
                return parseNull(ch);
            case 't':
                return parseTrue(ch);
            case 'f':
                return parseFalse(ch);
            default:
                if (Chars.isDigit(ch) || ch == '-')
                    return parseNumber(ch);
                break;
        }
        return new JsonToken(JsonToken.Type.Unexpect, takeBuffer(), linenum, offset);
    }

    private JsonToken parseTrue(char ch) throws IOException {
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'r')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'u')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'e')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        return new JsonToken(Type.Boolean, takeBuffer(), linenum, offset);
    }

    private JsonToken parseFalse(char ch) throws IOException {
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'a')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'l')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 's')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'e')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        return new JsonToken(Type.Null, takeBuffer(), linenum, offset);
    }

    private JsonToken parseNull(char ch) throws IOException {
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'u')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'l')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (isEof())
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        if (next() != 'l')
            return new JsonToken(Type.Unexpect, takeBuffer(), linenum, offset);
        return new JsonToken(Type.Null, takeBuffer(), linenum, offset);
    }

    // 解析字符串
    private JsonToken parseString() throws IOException {
        while (!isEof()) {
            char ch = next();
            switch (ch) {
                case '\\': // 需要进行转义
                    if (!parseEscapeString())
                        return new JsonToken(JsonToken.Type.Unexpect, takeBuffer(), linenum, offset);
                    break;
                case '\n':
                    return new JsonToken(JsonToken.Type.Unexpect, takeBuffer(), linenum, offset);
                case '"':
                    return new JsonToken(JsonToken.Type.String, takeBuffer(), linenum, offset);
                default:
                    buffer.append(ch);
                    break;
            }
        }

        return new JsonToken(JsonToken.Type.Unexpect, takeBuffer(), linenum, offset);
    }

    private boolean parseEscapeString() throws IOException {
        if (isEof())
            return false;
        char ch = next();
        switch (ch) {
            case 't':
                buffer.append('\t');
                break;
            case 'f':
                buffer.append('\f');
                break;
            case 'r':
                buffer.append('\r');
                break;
            case 'n':
                buffer.append('\n');
                break;
            case '\\':
                buffer.append('\\');
                break;
            case 'u':
                return parseUnicode();
            default:
                buffer.append(ch);
                break;
        }
        return true;
    }

    private boolean parseUnicode() throws IOException {
        if (isEof())
            return false;
        char[] hex = new char[4];
        hex[0] = next();
        if (isEof())
            return false;
        hex[1] = next();
        if (isEof())
            return false;
        hex[2] = next();
        if (isEof())
            return false;
        hex[3] = next();

        for (int i = 0; i < hex.length; i++) {
            if (!Chars.isXDigit(hex[i]))
                return false;
        }

        char ch = (char) Integer.parseInt(new String(hex), 16);
        buffer.append(ch);
        return true;
    }

    private JsonToken parseNumber(char firstCh) throws IOException {
        buffer.append(firstCh);
        boolean hasDot = false;
        while (!isEof()) {
            char ch = (char) curCh;
            if (ch == '.') {
                if (hasDot || buffer.length() == 0 || (firstCh == '-' && buffer.length() == 1))
                    return new JsonToken(JsonToken.Type.Unexpect, takeBuffer(), linenum, offset);
                hasDot = true;
            } else if (!Chars.isDigit(ch)) {
                break;
            }
            buffer.append(ch);
            next();
        }

        return new JsonToken(hasDot ? JsonToken.Type.Double : JsonToken.Type.Integer, takeBuffer(), linenum, offset);
    }
}
