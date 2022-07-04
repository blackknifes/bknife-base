package com.bknife.base.converter.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bknife.base.converter.Converter;

public class StringToSqlTime implements Converter<String, Time> {
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    @Override
    public Time convert(String from) {
        try {
            return new Time(dateFormat.parse(from).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
