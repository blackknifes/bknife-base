package com.bknife.base.json.deserializer;

import java.io.IOException;

import com.bknife.base.json.deserializer.JsonToken.Type;
import com.bknife.base.json.exception.JsonParserError;

public class JsonParser {
    private JsonLexical lexical;
    private JsonVisitor visitor;

    public JsonParser(JsonLexical lexical, JsonVisitor visitor) {
        this.lexical = lexical;
        this.visitor = visitor;
    }

    public boolean parse() throws JsonParserError, IOException {
        return parseValue(lexical.nextToken());
    }

    public boolean parseValue(JsonToken token) throws JsonParserError, IOException {
        switch (token.getType()) {
            case ObjectOpen:
                if (!visitor.visitObjectBegin())
                    return false;
                return parseObject();
            case ArrayOpen:
                if (!visitor.visitArrayBegin())
                    return false;
                return parseArray();
            case String:
                return visitor.visitValue(token.getToken());
            case Integer: {
                long value = Long.parseLong(token.getToken());
                if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE)
                    return visitor.visitValue((int) value);
                else
                    return visitor.visitValue(value);
            }
            case Boolean:
                return visitor.visitValue("true".equals(token.getToken()));
            case Null:
                return visitor.visitValue(null);
            case Double:
                return visitor.visitValue(Double.parseDouble(token.getToken()));
            default:
                break;
        }
        throw new JsonParserError(token);
    }

    public boolean parseObject() throws JsonParserError, IOException {
        JsonToken token = lexical.nextToken();

        while (true) {
            if (token.getType() == Type.ObjectClose)
                return visitor.visitObjectEnd();

            if (token.getType() != Type.String)
                throw new JsonParserError(token);
            if (!visitor.visitKey(token.getToken()))
                return false;
            JsonToken colonToken = lexical.nextToken();
            if (colonToken.getType() != Type.Colon)
                throw new JsonParserError(colonToken);
            if (!parseValue(lexical.nextToken()))
                return false;

            token = lexical.nextToken();
            if (token.getType() == Type.Comma)
                token = lexical.nextToken();
        }
    }

    public boolean parseArray() throws JsonParserError, IOException {
        JsonToken token = lexical.nextToken();

        while (true) {
            if (token.getType() == Type.ArrayClose)
                return visitor.visitArrayEnd();

            if (!parseValue(token))
                return false;
            token = lexical.nextToken();
            if (token.getType() == Type.Comma)
                token = lexical.nextToken();
        }
    }
}
