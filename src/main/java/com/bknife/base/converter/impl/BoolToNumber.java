package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class BoolToNumber implements Converter<Boolean, Number>{

    @Override
    public Number convert(Boolean from) {
        return from? 1 : 0;
    }    
}
