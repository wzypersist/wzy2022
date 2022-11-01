package com.wzy.mySpring.test;

import com.wzy.mySpring.annotation.Autowired;
import com.wzy.mySpring.annotation.Component;

@Component
public class Account {

    @Autowired
    private User user;

}
