package com.bknife.base.converter.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bknife.base.converter.Converter;

public class StringToSqlDate implements Converter<String, Date> {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String from) {
        try {
            return new Date(dateFormat.parse(from).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
