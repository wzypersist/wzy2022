package com.wzy.mySpring.annotation.test;

import com.wzy.mySpring.annotation.context.annotation.Autowired;
import com.wzy.mySpring.annotation.context.annotation.Component;
import com.wzy.mySpring.annotation.context.annotation.Value;

@Component
public class User {

    @Autowired
    private Account account;

    @Value("hhhh")
    private String testValue;

    public void test(){
        System.out.println(account);
        System.out.println(testValue);
    }

}
