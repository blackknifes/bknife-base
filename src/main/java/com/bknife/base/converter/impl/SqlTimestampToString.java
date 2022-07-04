package com.bknife.base.converter.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.bknife.base.converter.Converter;

public class SqlTimestampToString implements Converter<Timestamp, String> {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convert(Timestamp from) {
        return dateFormat.format(from);
    }
}
