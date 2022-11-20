package com.wzy.mySpring.xml.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.DisposableBean;
import com.wzy.mySpring.xml.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {
    
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean)){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(destroyMethod == null){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
        
    }
}
