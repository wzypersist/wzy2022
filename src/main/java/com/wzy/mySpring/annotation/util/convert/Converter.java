package com.wzy.mySpring.annotation.util.convert;

public interface Converter<S,T> {

    T convert(S s);

}
