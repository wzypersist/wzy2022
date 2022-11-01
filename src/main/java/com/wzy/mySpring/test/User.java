package com.wzy.mySpring.test;

import com.wzy.mySpring.annotation.Autowired;
import com.wzy.mySpring.annotation.Component;
import com.wzy.mySpring.annotation.Value;

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
