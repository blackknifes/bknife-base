package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToFloat implements Converter<String, Float> {

    @Override
    public Float convert(String from) {
        return Float.parseFloat(from);
    }
}
