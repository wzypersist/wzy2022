package com.wzy.mySpring.annotation.util.convert;

public interface ConverterFactory<S,R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
