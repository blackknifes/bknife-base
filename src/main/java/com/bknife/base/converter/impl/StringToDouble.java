package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToDouble implements Converter<String, Double> {

    @Override
    public Double convert(String from) {
        return Double.parseDouble(from);
    }
}
