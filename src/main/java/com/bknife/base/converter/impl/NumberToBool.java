package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToBool implements Converter<Number, Boolean> {

    @Override
    public Boolean convert(Number from) {
        return from.longValue() != 0;
    }
}
