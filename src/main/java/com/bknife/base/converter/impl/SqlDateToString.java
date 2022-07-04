package com.bknife.base.converter.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.bknife.base.converter.Converter;

public class SqlDateToString implements Converter<Date, String> {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String convert(Date from) {
        return dateFormat.format(from);
    }

}
