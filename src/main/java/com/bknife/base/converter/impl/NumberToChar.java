package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class NumberToChar implements Converter<Number, Character>{

    @Override
    public Character convert(Number from) {
        return (char)from.shortValue();
    }    
}
