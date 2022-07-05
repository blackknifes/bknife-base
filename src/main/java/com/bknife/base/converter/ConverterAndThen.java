package com.bknife.base.converter;

public class ConverterAndThen<FROM, PROXY, TO> implements Converter<FROM, TO> {
    private Converter<FROM, PROXY> firstConverter;
    private Converter<PROXY, TO> thenConverter;

    public ConverterAndThen(Converter<FROM, PROXY> firstConverter, Converter<PROXY, TO> thenConverter) {
        this.firstConverter = firstConverter;
        this.thenConverter = thenConverter;
    }

    @Override
    public TO convert(FROM from) {
        PROXY proxy = firstConverter.convert(from);
        if (proxy == null)
            return null;
        return thenConverter.convert(proxy);
    }
}
