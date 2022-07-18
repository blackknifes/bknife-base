package com.bknife.base.json.exception;

import com.bknife.base.json.deserializer.JsonToken;

public class JsonParserError extends Error {
    private JsonToken token;

    public JsonParserError(JsonToken token) {
        super("unexpect token " + token.getToken() + " , linenum=" + token.getLinenum() + ", offset="
                + token.getOffset());
        this.token = token;
    }

    public JsonToken getToken() {
        return token;
    }

}
