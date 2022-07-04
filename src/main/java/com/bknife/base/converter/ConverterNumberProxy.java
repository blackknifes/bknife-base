package com.bknife.base.converter;

public class ConverterNumberProxy<FROM, TO> implements Converter<FROM, TO> {
    private Converter<FROM, Number> toNumberConverter;
    private Converter<Number, TO> fromNumberConverter;

    public ConverterNumberProxy(Converter<FROM, Number> toNumberConverter, Converter<Number, TO> fromNumberConverter) {
        this.toNumberConverter = toNumberConverter;
        this.fromNumberConverter = fromNumberConverter;
    }

    @Override
    public TO convert(FROM from) {
        Number number = toNumberConverter.convert(from);
        if (number == null)
            return null;
        return fromNumberConverter.convert(number);
    }

}
