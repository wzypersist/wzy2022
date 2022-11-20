package com.wzy.mySpring.xml.context.event;

import com.wzy.mySpring.xml.context.ApplicationEvent;
import com.wzy.mySpring.xml.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    
    void addApplicationListener(ApplicationListener<?> listener);
    
    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
    
    
}
