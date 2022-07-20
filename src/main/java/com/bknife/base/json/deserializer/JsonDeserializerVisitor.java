package com.bknife.base.json.deserializer;

public class JsonDeserializerVisitor<T> implements JsonVisitor {
    private T result;

    public JsonDeserializerVisitor()
    {

    }

    @Override
    public boolean visitArrayBegin() {
        // TODO Auto-generated method stub
        return JsonVisitor.super.visitArrayBegin();
    }

    @Override
    public boolean visitArrayEnd() {
        // TODO Auto-generated method stub
        return JsonVisitor.super.visitArrayEnd();
    }

    @Override
    public boolean visitKey(String key) {
        // TODO Auto-generated method stub
        return JsonVisitor.super.visitKey(key);
    }

    @Override
    public boolean visitObjectBegin() {
        // TODO Auto-generated method stub
        return JsonVisitor.super.visitObjectBegin();
    }

    @Override
    public boolean visitObjectEnd() {
        // TODO Auto-generated method stub
        return JsonVisitor.super.visitObjectEnd();
    }

    @Override
    public boolean visitValue(Object value) {
        // TODO Auto-generated method stub
        return JsonVisitor.super.visitValue(value);
    }

}
