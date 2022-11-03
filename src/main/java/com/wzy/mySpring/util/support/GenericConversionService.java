package com.wzy.mySpring.util.support;

import com.wzy.mySpring.util.ConversionService;
import com.wzy.mySpring.util.convert.Converter;
import com.wzy.mySpring.util.convert.ConverterFactory;
import com.wzy.mySpring.util.convert.ConverterRegistry;
import com.wzy.mySpring.util.convert.GenericConverter;
import com.wzy.mySpring.util.convert.GenericConverter.ConvertiblePair;

import java.util.HashMap;
import java.util.Map;

public class GenericConversionService implements ConversionService, ConverterRegistry {

    private Map<ConvertiblePair,GenericConverter> converters = new HashMap<>();

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {

        return false;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return null;
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {

    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {

    }

    @Override
    public void addConverter(GenericConverter converter) {

    }
}
