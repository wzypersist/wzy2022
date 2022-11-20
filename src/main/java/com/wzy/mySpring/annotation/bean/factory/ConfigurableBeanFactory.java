package com.wzy.mySpring.annotation.bean.factory;

import com.wzy.mySpring.annotation.bean.factory.config.BeanPostProcessor;
import com.wzy.mySpring.annotation.bean.factory.config.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory , SingletonBeanRegistry {

    /**
     * 销毁单例bean
     */
    void destroySingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
