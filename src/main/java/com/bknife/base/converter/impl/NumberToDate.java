package com.bknife.base.converter.impl;

import java.util.Date;

import com.bknife.base.converter.Converter;

public class NumberToDate implements Converter<Number, Date> {

    @Override
    public Date convert(Number from) {
        return new Date(from.longValue());
    }
}
