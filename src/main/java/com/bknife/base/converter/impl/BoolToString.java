package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class BoolToString implements Converter<Boolean, String> {

    @Override
    public String convert(Boolean from) {
        return from.booleanValue() ? "true" : "false";
    }

}
