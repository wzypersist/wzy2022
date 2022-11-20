package com.wzy.mySpring.annotation.test;

import com.wzy.mySpring.annotation.bean.PropertyValue;
import com.wzy.mySpring.annotation.bean.PropertyValues;
import com.wzy.mySpring.annotation.context.MyApplicationContext;

public class Test {

    public static void main(String[] args) {
        MyApplicationContext context = new MyApplicationContext(AppConfig.class);
        User user = (User) context.getBean("user");
        user.test();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("a","b"));
        propertyValues.addPropertyValue(new PropertyValue("aaa","bcc"));
        PropertyValue[] propertyValues1 = propertyValues.getPropertyValues();
        System.out.println(propertyValues1[2].getName());
    }

}
