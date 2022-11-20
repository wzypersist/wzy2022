package com.wzy.mySpring.xml.beans.factory;

public interface FactoryBean<T> {
    
    T getObject() throws Exception;
    
    Class<?> getObjectType();
    
    boolean isSingleton();
    
}
