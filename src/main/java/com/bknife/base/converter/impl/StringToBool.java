package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToBool implements Converter<String, Boolean> {

    @Override
    public Boolean convert(String from) {
        return !"false".equalsIgnoreCase(from);
    }
}
