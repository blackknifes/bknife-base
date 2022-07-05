package com.bknife.base.converter;

public interface Converter<FROM, TO> {
    public TO convert(FROM from);

    @SuppressWarnings("unchecked")
    default <FINAL> Converter<FROM, FINAL> andThen(Converter<? super TO, ? extends FINAL> after) {
        return new ConverterAndThen<FROM, TO, FINAL>(this, (Converter<TO, FINAL>) after);
    }
}
