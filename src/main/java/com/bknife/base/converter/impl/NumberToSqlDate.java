package com.bknife.base.converter.impl;

import java.sql.Date;

import com.bknife.base.converter.Converter;

public class NumberToSqlDate implements Converter<Number, Date> {

    @Override
    public Date convert(Number from) {
        return new Date(from.longValue());
    }
}
