package com.wzy.mySpring.util.support;

import com.wzy.mySpring.util.convert.Converter;
import com.wzy.mySpring.util.convert.ConverterFactory;

public class StringToNumberConverterFactory implements ConverterFactory<String ,Number> {



    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumberConverter<>(targetType);
    }

    private final class StringToNumberConverter<T extends Number> implements Converter<String ,T>{

        private final Class<?> targetType;

        public StringToNumberConverter(Class<?> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if(source.length() == 0){
                return null;
            }
            if(targetType.equals(Integer.class)){
                return (T) Integer.valueOf(source);
            }else if(targetType.equals(Long.class)){
                return (T) Long.valueOf(source);
            }else{
                throw new IllegalArgumentException(
                        "Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
            }
        }
    }
}
