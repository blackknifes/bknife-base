package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToString implements Converter<Number, String> {

    @Override
    public String convert(Number from) {
        return from.toString();
    }
}
