package com.wzy.mySpring.xml.context.event;

import com.wzy.mySpring.xml.beans.factory.BeanFactory;
import com.wzy.mySpring.xml.context.ApplicationEvent;
import com.wzy.mySpring.xml.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
