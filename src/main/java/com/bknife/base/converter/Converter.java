package com.bknife.base.converter;

public interface Converter<FROM, TO> {
    public TO convert(FROM from);
}
