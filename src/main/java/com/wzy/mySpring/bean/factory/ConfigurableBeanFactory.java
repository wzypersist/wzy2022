package com.wzy.mySpring.bean.factory;

import com.wzy.mySpring.bean.factory.config.BeanPostProcessor;
import com.wzy.mySpring.bean.factory.config.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory , SingletonBeanRegistry {

    /**
     * 销毁单例bean
     */
    void destroySingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
