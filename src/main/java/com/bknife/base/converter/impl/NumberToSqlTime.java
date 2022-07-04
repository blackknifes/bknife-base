package com.bknife.base.converter.impl;

import java.sql.Time;

import com.bknife.base.converter.Converter;

public class NumberToSqlTime implements Converter<Number, Time> {

    @Override
    public Time convert(Number from) {
        return new Time(from.longValue());
    }

}
