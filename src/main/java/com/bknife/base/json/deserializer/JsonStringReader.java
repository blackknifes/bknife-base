package com.bknife.base.json.deserializer;

import com.bknife.base.json.JsonReader;
import com.bknife.base.util.Chars;

public class JsonStringReader implements JsonReader {
    private final String content; // 等待读取的字符串
    private int index = 0; // 字符串索引
    private int linenum = 0; // 行数
    private int offset = 0; // 列数
    private int preLinenum = 0; // 行数
    private int preOffset = 0; // 列数

    public JsonStringReader(String content) {
        this.content = content;
    }

    @Override
    public void skipSpace() {
        char ch;
        while (index < content.length() && Chars.isSpace(ch = content.charAt(index))) {
            ++index;
            preOffset = offset;
            if (ch == '\n') {
                preLinenum = linenum;
                ++linenum;
                offset = 0;
            } else
                ++offset;
        }
    }

    @Override
    public char next() {
        char ch = content.charAt(index++);
        preOffset = offset;
        if (ch == '\n') {
            preLinenum = linenum;
            ++linenum;
            offset = 0;
        } else
            ++offset;
        return ch;
    }

    @Override
    public void back() {
        --index;
        linenum = preLinenum;
        offset = preOffset;
    }

    @Override
    public boolean isEof() {
        return index >= content.length();
    }

    @Override
    public int linenum() {
        return linenum;
    }

    @Override
    public int offset() {
        return offset;
    }

    @Override
    public void reset() {
        index = preLinenum = preOffset = linenum = offset = 0;
    }
}
