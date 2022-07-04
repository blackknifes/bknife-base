package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToFloat implements Converter<Number, Float> {

    @Override
    public Float convert(Number from) {
        return from.floatValue();
    }
}
