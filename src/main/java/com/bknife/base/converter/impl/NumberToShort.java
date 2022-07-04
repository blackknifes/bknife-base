package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToShort implements Converter<Number, Short> {

    @Override
    public Short convert(Number from) {
        return from.shortValue();
    }
}
