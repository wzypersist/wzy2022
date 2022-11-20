package com.wzy.event;

import com.wzy.mySpring.xml.context.ApplicationListener;
import com.wzy.mySpring.xml.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
