package com.wzy.mySpring.test;

import com.wzy.mySpring.context.annotation.Autowired;
import com.wzy.mySpring.context.annotation.Component;

@Component
public class Account {

    @Autowired
    private User user;

}
