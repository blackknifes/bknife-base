package com.bknife.base.converter.impl;

import java.math.BigDecimal;

import com.bknife.base.converter.Converter;

public class NumberToBigDecimal implements Converter<Number, BigDecimal> {

    @Override
    public BigDecimal convert(Number from) {
        return new BigDecimal(from.toString());
    }
}
