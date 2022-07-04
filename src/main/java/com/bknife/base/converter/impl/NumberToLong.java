package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToLong implements Converter<Number, Long> {

    @Override
    public Long convert(Number from) {
        return from.longValue();
    }
}
