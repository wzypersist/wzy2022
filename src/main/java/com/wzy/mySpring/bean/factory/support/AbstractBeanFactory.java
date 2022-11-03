package com.wzy.mySpring.bean.factory.support;

import com.wzy.mySpring.bean.factory.ConfigurableBeanFactory;

/**
 * AbstractBeanFactory继承DefaultSingletonBeanRegistry并实现ConfigurableBeanFactory
 * @author wenzhaoyu
 * @date 2022/11/3
 */

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    
    @Override
    public void getBean(String beanName){
        Object instance = getSingleton(beanName);
        if(instance != null){

        }
    }
}
