package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToByte implements Converter<Number, Byte>{

    @Override
    public Byte convert(Number from) {
        return from.byteValue();
    }    
}
