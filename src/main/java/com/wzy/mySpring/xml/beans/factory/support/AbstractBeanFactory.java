package com.wzy.mySpring.xml.beans.factory.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.FactoryBean;
import com.wzy.mySpring.xml.beans.factory.config.BeanDefinition;
import com.wzy.mySpring.xml.beans.factory.config.BeanPostProcessor;
import com.wzy.mySpring.xml.beans.factory.config.ConfigurableBeanFactory;
import com.wzy.mySpring.xml.util.ClassUtils;
import com.wzy.mySpring.xml.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    
    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
    
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }
    @Override
    public Object getBean(String name,Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    private <T> T doGetBean(String name, Object[] args) {
        Object sharedInstance = getSingleton(name);
        if(sharedInstance != null){
            return (T)getObjectForBeanInstance(sharedInstance,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        
        return (T)getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if(!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if(object == null){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    protected abstract Object createBean(String name, BeanDefinition beanDefinition,Object[] args) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
    
    public ClassLoader getClassLoader(){
        return this.classLoader;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }
    
    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }
}
