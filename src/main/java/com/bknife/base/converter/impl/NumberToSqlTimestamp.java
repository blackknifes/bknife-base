package com.bknife.base.converter.impl;

import java.sql.Timestamp;

import com.bknife.base.converter.Converter;

public class NumberToSqlTimestamp implements Converter<Number, Timestamp> {

    @Override
    public Timestamp convert(Number from) {
        return new Timestamp(from.longValue());
    }
}
