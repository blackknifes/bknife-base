package com.bknife.base.converter.impl;

import java.math.BigDecimal;

import com.bknife.base.converter.Converter;

public class StringToBigDecimal implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convert(String from) {
        return new BigDecimal(from);
    }

}
