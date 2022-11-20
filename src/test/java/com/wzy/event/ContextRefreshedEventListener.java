package com.wzy.event;


import com.wzy.mySpring.xml.context.ApplicationListener;
import com.wzy.mySpring.xml.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
