package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToDouble implements Converter<Number, Double> {

    @Override
    public Double convert(Number from) {
        return from.doubleValue();
    }
}
