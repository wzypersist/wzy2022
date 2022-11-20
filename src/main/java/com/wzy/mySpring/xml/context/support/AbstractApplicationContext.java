package com.wzy.mySpring.xml.context.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.ConfigurableListableBeanFactory;
import com.wzy.mySpring.xml.beans.factory.config.BeanFactoryPostProcessor;
import com.wzy.mySpring.xml.beans.factory.config.BeanPostProcessor;
import com.wzy.mySpring.xml.context.ApplicationEvent;
import com.wzy.mySpring.xml.context.ApplicationListener;
import com.wzy.mySpring.xml.context.ConfigurableApplicationContext;
import com.wzy.mySpring.xml.context.event.ApplicationEventMulticaster;
import com.wzy.mySpring.xml.context.event.ContextClosedEvent;
import com.wzy.mySpring.xml.context.event.ContextRefreshedEvent;
import com.wzy.mySpring.xml.context.event.SimpleApplicationEventMulticaster;
import com.wzy.mySpring.xml.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;
    
    @Override
    public void refresh() throws BeansException {
        // 1.创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        
        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory =  getBeanFactory();
        
        // 3.添加 ApplicationContextAwareProcessor，
        // 让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        
        // 4.在Bean实例化之前，执行BeanFactoryPostProcessor,在容器中注册bean
        invokeBeanFactoryPostProcessors(beanFactory);
        
        // 5.BeanPostProcessor需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        
        // 6.初始化事件发布者
        initApplicationEventMulticaster();

        // 7.注册事件监听器
        registerListeners();

        // 8.提前实例化单例Bean
        beanFactory.preInstantiateSingletons();
        
        // 9.发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }
    
    private void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }


    protected abstract void refreshBeanFactory() throws BeansException;
    
    protected abstract ConfigurableListableBeanFactory getBeanFactory() throws BeansException;
    
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public void close(){
        publishEvent(new ContextClosedEvent(this));
        
        getBeanFactory().destroySingletons();
    }
}
