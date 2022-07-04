package com.bknife.base.converter.impl;

import java.util.Date;

import com.bknife.base.converter.Converter;

public class DateToNumber implements Converter<Date, Number> {

    @Override
    public Number convert(Date from) {
        return from.getTime();
    }
}
