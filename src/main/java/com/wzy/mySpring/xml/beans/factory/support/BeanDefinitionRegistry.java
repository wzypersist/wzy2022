package com.wzy.mySpring.xml.beans.factory.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
    boolean containsBeanDefinition(String beanName);
    
    String[] getBeanDefinitionNames();
    
}
