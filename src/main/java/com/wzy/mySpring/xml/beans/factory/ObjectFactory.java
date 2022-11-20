package com.wzy.mySpring.xml.beans.factory;

import com.wzy.mySpring.xml.beans.BeansException;

public interface ObjectFactory<T> {
    
    T getObject() throws BeansException;
    
}
