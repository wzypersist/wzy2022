package com.wzy.mySpring.xml.beans.factory;

import com.wzy.mySpring.xml.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册的所有bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
    
}
