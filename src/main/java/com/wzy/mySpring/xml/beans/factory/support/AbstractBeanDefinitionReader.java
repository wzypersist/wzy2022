package com.wzy.mySpring.xml.beans.factory.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.core.io.DefaultResourceLoader;
import com.wzy.mySpring.xml.core.io.Resource;
import com.wzy.mySpring.xml.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    
    private final BeanDefinitionRegistry registry;
    
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, DefaultResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
    
}
