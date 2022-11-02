package com.wzy.mySpring.util.convert;

public interface Converter<S,T> {

    T convert(S s);

}
