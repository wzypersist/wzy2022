package com.wzy.mySpring.xml.beans.factory.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.config.BeanDefinition;
import com.wzy.mySpring.xml.core.io.Resource;
import com.wzy.mySpring.xml.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    
    BeanDefinitionRegistry getRegistry();
    
    ResourceLoader getResourceLoader();
    
    void loadBeanDefinitions(Resource resource) throws BeansException;
    
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    
    void loadBeanDefinitions(String location) throws BeansException;
    
    void loadBeanDefinitions(String... locations) throws BeansException;
    
    
    
}
