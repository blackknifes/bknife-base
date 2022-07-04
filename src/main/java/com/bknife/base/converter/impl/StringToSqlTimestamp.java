package com.bknife.base.converter.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bknife.base.converter.Converter;

public class StringToSqlTimestamp implements Converter<String, Timestamp> {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public Timestamp convert(String from) {
        try {
            return new Timestamp(dateFormat.parse(from).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
