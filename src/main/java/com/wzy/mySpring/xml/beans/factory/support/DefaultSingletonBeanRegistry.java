package com.wzy.mySpring.xml.beans.factory.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.DisposableBean;
import com.wzy.mySpring.xml.beans.factory.ObjectFactory;
import com.wzy.mySpring.xml.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    
    protected static final Object NULL_OBJECT = new Object();
    
    // 一级缓存 存入走完生命周期的bean
    private  Map<String, Object> singletonObjects = new HashMap<>();
    // 二级缓存，存没有走完生命周期的对象
    protected final Map<String,Object> earlySingletonObjects = new HashMap<>();
    // 三级缓存，存放代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(); 
    
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    
    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if(singletonObject == null){
            singletonObject = earlySingletonObjects.get(beanName);
            if(singletonObject == null){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if(singletonFactory != null){
                    singletonObject = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName,singletonObject);
                    singletonObjects.put(beanName,singletonObject);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName,ObjectFactory<?> singletonFactory){
        if(!earlySingletonObjects.containsKey(beanName)){
            this.singletonFactories.put(beanName,singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Object[] disposableBeanNames = this.disposableBeans.keySet().toArray();
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
    
}
