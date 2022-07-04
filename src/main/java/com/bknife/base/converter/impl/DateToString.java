package com.bknife.base.converter.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bknife.base.converter.Converter;

public class DateToString implements Converter<Date, String> {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String convert(Date from) {
        return dateFormat.format(from);
    }

}
