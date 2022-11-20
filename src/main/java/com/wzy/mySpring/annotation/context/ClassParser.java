package com.wzy.mySpring.annotation.context;

import com.wzy.mySpring.annotation.bean.factory.config.BeanDefinition;
import lombok.Data;
import com.wzy.mySpring.annotation.context.annotation.Component;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClassParser {

    private Map<String, BeanDefinition> beanDefinitionMap;

    public void scan(Class<?> clazz){
        beanDefinitionMap = new HashMap<>();

        if (clazz.isAnnotationPresent(Component.class)) {

        }



    }
}
