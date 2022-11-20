package com.wzy.mySpring.xml.beans.factory.config;

import com.wzy.mySpring.xml.beans.factory.HierarchicalBeanFactory;
import com.wzy.mySpring.xml.util.StringValueResolver;

public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {
    
    String SCOPE_SINGLETON = "singleton";
    
    String SCOPE_PROTOTYPE = "prototype";
    
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
    
    
    
}
