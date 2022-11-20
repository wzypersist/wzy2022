package com.wzy.mySpring.xml.beans.factory;

import com.wzy.mySpring.xml.beans.BeansException;
import com.wzy.mySpring.xml.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {
    
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
    
}
