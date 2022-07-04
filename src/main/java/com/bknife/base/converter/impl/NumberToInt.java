package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToInt implements Converter<Number, Integer> {

    @Override
    public Integer convert(Number from) {
        return from.intValue();
    }

}
