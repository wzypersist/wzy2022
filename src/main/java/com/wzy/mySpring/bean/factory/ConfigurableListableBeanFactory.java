package com.wzy.mySpring.bean.factory;

import com.wzy.mySpring.bean.factory.config.BeanDefinition;
import com.wzy.mySpring.bean.factory.config.BeanPostProcessor;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory,ConfigurableBeanFactory {

    /**
     * 根据名称查找BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    void preInstantiateSingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
