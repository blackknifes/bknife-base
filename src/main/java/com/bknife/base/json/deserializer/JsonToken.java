package com.bknife.base.json.deserializer;

import com.bknife.base.json.JsonReader;

public class JsonToken {
    public static enum Type {
        ObjectOpen, // {
        ObjectClose, // }
        ArrayOpen, // [
        ArrayClose, // ]
        Comma, // ,
        Colon, // :
        String, // 字符串
        Integer, // 整形
        Boolean, // bool值
        Null, // 空
        Double, // 浮点
        Eof, // 文件结束
        Unexpect // 不期望的字符
    }

    private Type type;
    private String token;
    private int linenum;
    private int offset;

    public JsonToken(Type type, String token, int linenum, int offset) {
        this.type = type;
        this.token = token;
        this.linenum = linenum;
        this.offset = offset;
    }

    public JsonToken(Type type, String token, JsonReader reader) {
        this.type = type;
        this.token = token;
        this.linenum = reader.linenum();
        this.offset = reader.offset();
    }

    public Type getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public int getLinenum() {
        return linenum;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "JsonToken [linenum=" + linenum + ", offset=" + offset + ", token=\"" + token + "\", type=" + type + "]";
    }
}
