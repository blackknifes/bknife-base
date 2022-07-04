package com.bknife.base.converter.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bknife.base.converter.Converter;

public class StringToDate implements Converter<String, Date> {
    private DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String from) {
        try {
            return datetimeFormat.parse(from);
        } catch (ParseException e) {
        }

        try {
            return dateFormat.parse(from);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
