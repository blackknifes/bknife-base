package com.bknife.base.json.serializer;

import com.bknife.base.util.TokenBuffer;

class JsonStringSerializer implements JsonSerializer<Object> {

    private void fillZero(TokenBuffer buffer, int len) {
        for (int i = 0; i < len; i++)
            buffer.append('0');
    }

    private void escape(TokenBuffer buffer, String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '\n':
                    buffer.append("\\\\n");
                    continue;
                case '\t':
                    buffer.append("\\\\t");
                    continue;
                case '\f':
                    buffer.append("\\\\f");
                    continue;
                case '\r':
                    buffer.append("\\\\r");
                    continue;
                case '"':
                    buffer.append("\\\\\"");
                    continue;
                default:
                    break;
            }

            if (ch < 0x20 || ch == 0x7F) {
                buffer.append("\\\\u");
                String tmp = Integer.toString((int) ch, 16);
                fillZero(buffer, 4 - tmp.length());
                buffer.append(tmp);
                continue;
            }
            buffer.append(ch);
        }
    }

    @Override
    public void serialize(JsonSerializer<Object> root, int depth, TokenBuffer buffer, Object value, JsonSerializeFeature feature) {
        buffer.doubleQuote();
        escape(buffer, value.toString());
        buffer.doubleQuote();
    }
}
