package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToLong implements Converter<String, Long> {

    @Override
    public Long convert(String from) {
        return Long.parseLong(from);
    }
}
