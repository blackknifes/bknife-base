package com.bknife.base.util;

public final class TokenBuffer {
    public StringBuffer buffer = new StringBuffer();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buffer == null) ? 0 : buffer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TokenBuffer other = (TokenBuffer) obj;
        if (buffer == null) {
            if (other.buffer != null)
                return false;
        } else if (!buffer.equals(other.buffer))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public TokenBuffer removeLast() {
        buffer.deleteCharAt(buffer.length() - 1);
        return this;
    }

    public TokenBuffer append(Object value) {
        buffer.append(value);
        return this;
    }

    public TokenBuffer string(Object value) {
        doubleQuote();
        String temp = value.toString();
        for (int i = 0; i < temp.length(); i++) {
            char ch = temp.charAt(i);
            if (ch == '"')
                append("\\");
            append(ch);
        }
        return doubleQuote();
    }

    public TokenBuffer space() {
        return append(" ");
    }

    public TokenBuffer space(int count) {
        for (int i = 0; i < count; i++)
            append(" ");
        return this;
    }

    public TokenBuffer openParenthese() {
        return append("(");
    }

    public TokenBuffer closeParenthese() {
        return append(")");
    }

    public TokenBuffer parenthese(Object val) {
        return openParenthese().append(val).closeParenthese();
    }

    public TokenBuffer openBracket() {
        return append("[");
    }

    public TokenBuffer closeBracket() {
        return append("]");
    }

    public TokenBuffer bracket(Object val) {
        return openBracket().append(val).closeBracket();
    }

    public TokenBuffer openAngleBracket() {
        return append("<");
    }

    public TokenBuffer closeAngleBracket() {
        return append(">");
    }

    public TokenBuffer angleBracket(Object val) {
        return openAngleBracket().append(val).closeAngleBracket();
    }

    public TokenBuffer openBrace() {
        return append("{");
    }

    public TokenBuffer closeBrace() {
        return append("}");
    }

    public TokenBuffer brace(Object val) {
        return openBrace().append(val).closeBrace();
    }

    public TokenBuffer exclamation() {
        return append("!");
    }

    public TokenBuffer question() {
        return append("?");
    }

    public TokenBuffer comma() {
        return append(",");
    }

    public TokenBuffer dot() {
        return append(".");
    }

    public TokenBuffer semicolon() {
        return append(";");
    }

    public TokenBuffer doubleQuote() {
        return append("\"");
    }

    public TokenBuffer singleQuote() {
        return append("'");
    }

    public TokenBuffer backquote() {
        return append("`");
    }

    public TokenBuffer colon() {
        return append(":");
    }

    public TokenBuffer star() {
        return append("*");
    }

    public TokenBuffer plus() {
        return append("+");
    }

    public TokenBuffer minus() {
        return append("-");
    }

    public TokenBuffer less() {
        return append("<");
    }

    public TokenBuffer equal() {
        return append("=");
    }

    public TokenBuffer greater() {
        return append(">");
    }

    public TokenBuffer slash() {
        return append("/");
    }

    public TokenBuffer backslash() {
        return append("\\");
    }

    public TokenBuffer bar() {
        return append("|");
    }

    public TokenBuffer underline() {
        return append("_");
    }

    public TokenBuffer dollar() {
        return append("$");
    }

    public TokenBuffer at() {
        return append("@");
    }

    public TokenBuffer hash() {
        return append("#");
    }

    public TokenBuffer percent() {
        return append("%");
    }

    public TokenBuffer and() {
        return append("&");
    }

    public TokenBuffer caret() {
        return append("^");
    }

    public TokenBuffer title() {
        return append("~");
    }

    public TokenBuffer cr() {
        return append("\r");
    }

    public TokenBuffer lf() {
        return append("\n");
    }

    public TokenBuffer page() {
        return append("\f");
    }

    public TokenBuffer indent() {
        return append("\t");
    }
}
