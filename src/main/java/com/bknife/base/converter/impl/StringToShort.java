package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToShort implements Converter<String, Short> {

    @Override
    public Short convert(String from) {
        return Short.parseShort(from);
    }

}
