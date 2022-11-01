package com.wzy.mySpring.test;

import com.wzy.mySpring.annotation.Autowired;
import com.wzy.mySpring.annotation.Component;

@Component
public class User {

    @Autowired
    private Account account;

    public void test(){
        System.out.println(account);
    }

}
