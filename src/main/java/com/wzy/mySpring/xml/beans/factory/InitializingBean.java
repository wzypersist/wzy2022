package com.wzy.mySpring.xml.beans.factory;

public interface InitializingBean {
    
    void afterPropertiesSet() throws Exception;
    
}
