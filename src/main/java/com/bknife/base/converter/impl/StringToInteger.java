package com.bknife.base.converter.impl;

import com.bknife.base.converter.Converter;

public class StringToInteger implements Converter<String, Integer>{

    @Override
    public Integer convert(String from) {
        return Integer.parseInt(from);
    }
    
}
