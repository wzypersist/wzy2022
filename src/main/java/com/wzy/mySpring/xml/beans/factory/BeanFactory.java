package com.wzy.mySpring.xml.beans.factory;

import com.wzy.mySpring.xml.beans.BeansException;

public interface BeanFactory {
    
    Object getBean(String name) throws BeansException;
    
    Object getBean(String name,Object... args) throws BeansException;
    
    <T> T getBean(String name,Class<T> requiredType) throws BeansException;
    
    <T> T getBean(Class<T> requiredType) throws BeansException;
    
}
