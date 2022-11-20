package com.wzy.mySpring.xml.context;

import com.wzy.mySpring.xml.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
    
    void registerShutdownHook();
    
    void close();
    
}
