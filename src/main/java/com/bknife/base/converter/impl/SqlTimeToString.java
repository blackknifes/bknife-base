package com.bknife.base.converter.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.bknife.base.converter.Converter;

public class SqlTimeToString implements Converter<Time, String> {
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public String convert(Time from) {
        return dateFormat.format(from);
    }
}
