package com.wzy.mySpring.xml.beans.factory;



import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.config.AutowireCapableBeanFactory;
import com.wzy.mySpring.xml.beans.factory.config.BeanDefinition;
import com.wzy.mySpring.xml.beans.factory.config.BeanPostProcessor;
import com.wzy.mySpring.xml.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
    void preInstantiateSingletons() throws BeansException;
    
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
