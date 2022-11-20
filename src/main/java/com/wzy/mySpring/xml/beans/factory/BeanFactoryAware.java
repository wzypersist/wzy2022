package com.wzy.mySpring.xml.beans.factory;



import com.wzy.mySpring.xml.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
    
}
