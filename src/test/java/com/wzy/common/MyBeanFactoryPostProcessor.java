package com.wzy.common;


import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.PropertyValue;
import com.wzy.mySpring.xml.beans.PropertyValues;
import com.wzy.mySpring.xml.beans.factory.ConfigurableListableBeanFactory;
import com.wzy.mySpring.xml.beans.factory.config.BeanDefinition;
import com.wzy.mySpring.xml.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
