package com.wzy.mySpring.bean.factory.support;

import com.wzy.mySpring.bean.factory.DisposableBean;
import com.wzy.mySpring.bean.factory.ObjectFactory;
import com.wzy.mySpring.bean.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    //一级缓存
    private Map<String ,Object> singletonObjects = new HashMap<>();

    //二级缓存
    private Map<String ,Object> earlySingletonObjects = new HashMap<>();

    //三级缓存
    private Map<String , ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String , DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonBean = singletonObjects.get(beanName);
        if(singletonBean == null){
            singletonBean = earlySingletonObjects.get(beanName);
            if(singletonBean == null){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if(singletonFactory != null){
                    singletonBean = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName,singletonBean);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonBean;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName,bean);
    }

    public void addSingletonFactory(String beanName,ObjectFactory<?> objectFactory){
        singletonFactories.put(beanName,objectFactory);
    }

    public void destorySingletons(){
        return;
    }
}
