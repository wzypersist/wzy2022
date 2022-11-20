package com.wzy.mySpring.annotation.util;

public interface ConversionService {

    boolean canConvert(Class<?> sourceType,Class<?> targetType);

    <T> T convert(Object source,Class<T> targetType);

}
