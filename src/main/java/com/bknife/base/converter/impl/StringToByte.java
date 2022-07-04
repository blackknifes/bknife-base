package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToByte implements Converter<String, Byte> {

    @Override
    public Byte convert(String from) {
        return Byte.parseByte(from);
    }

}
