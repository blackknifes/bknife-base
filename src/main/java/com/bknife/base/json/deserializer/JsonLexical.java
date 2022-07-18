package com.bknife.base.json.deserializer;

import com.bknife.base.json.JsonReader;
import com.bknife.base.json.deserializer.JsonToken.Type;
import com.bknife.base.util.Chars;

public class JsonLexical {
    private JsonReader reader;

    private StringBuffer buffer = new StringBuffer();

    public JsonLexical(JsonReader reader) {
        this.reader = reader;
    }

    public JsonToken nextToken() {
        // 跳过空白字符
        reader.skipSpace();
        if (reader.isEof())
            return new JsonToken(JsonToken.Type.Eof, "", reader);
        char ch = reader.next();
        switch (ch) {
            case '{':
                return new JsonToken(JsonToken.Type.ObjectOpen, Character.toString(ch), reader);
            case '}':
                return new JsonToken(JsonToken.Type.ObjectClose, Character.toString(ch), reader);
            case '[':
                return new JsonToken(JsonToken.Type.ArrayOpen, Character.toString(ch), reader);
            case ']':
                return new JsonToken(JsonToken.Type.ArrayClose, Character.toString(ch), reader);
            case ',':
                return new JsonToken(JsonToken.Type.Comma, Character.toString(ch), reader);
            case ':':
                return new JsonToken(JsonToken.Type.Colon, Character.toString(ch), reader);
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
        return new JsonToken(JsonToken.Type.Unexpect, Character.toString(ch), reader);
    }

    private JsonToken parseTrue(char ch) {
        char nextCh;
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'r')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'u')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'e')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        return new JsonToken(Type.Boolean, "true", reader);
    }

    private JsonToken parseFalse(char ch) {
        char nextCh;
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'a')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'l')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 's')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'e')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        return new JsonToken(Type.Null, "false", reader);
    }

    private JsonToken parseNull(char ch) {
        char nextCh;
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'u')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'l')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        if (reader.isEof())
            return new JsonToken(Type.Unexpect, "EOF", reader);
        if ((nextCh = reader.next()) != 'l')
            return new JsonToken(Type.Unexpect, Character.toString(nextCh), reader);
        return new JsonToken(Type.Null, "null", reader);
    }

    // 解析字符串
    private JsonToken parseString() {
        while (!reader.isEof()) {
            char ch = reader.next();
            switch (ch) {
                case '\\': // 需要进行转义
                    if (!parseEscapeString())
                        return new JsonToken(JsonToken.Type.Unexpect, Character.toString(ch), reader);
                    break;
                case '\n':
                    return new JsonToken(JsonToken.Type.Unexpect, Character.toString(ch), reader);
                case '"':
                    JsonToken token = new JsonToken(JsonToken.Type.String, buffer.toString(), reader);
                    buffer.delete(0, buffer.length());
                    return token;
                default:
                    buffer.append(ch);
                    break;
            }
        }

        return new JsonToken(JsonToken.Type.Unexpect, "EOF", reader);
    }

    private boolean parseEscapeString() {
        if (reader.isEof())
            return false;
        char ch = reader.next();
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

    private boolean parseUnicode() {
        if (reader.isEof())
            return false;
        char[] hex = new char[4];
        hex[0] = reader.next();
        if (reader.isEof())
            return false;
        hex[1] = reader.next();
        if (reader.isEof())
            return false;
        hex[2] = reader.next();
        if (reader.isEof())
            return false;
        hex[3] = reader.next();

        for (int i = 0; i < hex.length; i++) {
            if (!Chars.isXDigit(hex[i]))
                return false;
        }

        char ch = (char) Integer.parseInt(new String(hex), 16);
        buffer.append(ch);
        return true;
    }

    private JsonToken parseNumber(char firstCh) {
        buffer.append(firstCh);
        boolean hasDot = false;
        while (!reader.isEof()) {
            char ch = reader.next();
            if (ch == '.') {
                if (hasDot || buffer.length() == 0 || (firstCh == '-' && buffer.length() == 1))
                    return new JsonToken(JsonToken.Type.Unexpect, ".", reader);
                hasDot = true;
            } else if (!Chars.isDigit(ch)) {
                reader.back();
                break;
            }
            buffer.append(ch);
        }

        String str = buffer.toString();
        buffer.delete(0, buffer.length());
        return new JsonToken(hasDot ? JsonToken.Type.Double : JsonToken.Type.Integer, str, reader);
    }
}
