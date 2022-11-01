package com.wzy.mySpring.test;

import com.wzy.mySpring.context.MyApplicationContext;

import java.io.File;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        MyApplicationContext context = new MyApplicationContext(AppConfig.class);
        User user = (User) context.getBean("user");
        user.test();
    }

}
