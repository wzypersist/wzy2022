package com.wzy.mySpring.annotation.test;

import com.wzy.mySpring.annotation.context.annotation.Autowired;
import com.wzy.mySpring.annotation.context.annotation.Component;

@Component
public class Account {

    @Autowired
    private User user;

}
