package com.wzy.mySpring.bean.factory.support;

import com.wzy.mySpring.bean.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     *  注册BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 根据beanName获取beanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 返回所有的Bean定义名称
     */
    String[] getBeanDefinitionNames();


}
