package com.wzy.mySpring.util.convert;

public interface ConverterFactory<S,R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
