package com.wzy.mySpring.xml.context.support;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.beans.factory.ConfigurableListableBeanFactory;
import com.wzy.mySpring.xml.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() throws BeansException {
        return beanFactory;
    }
    
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
