package com.wzy.mySpring.context;

import com.wzy.mySpring.annotation.Component;
import com.wzy.mySpring.annotation.Value;

import java.lang.reflect.Field;

@Component
public class ValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        for (Field field : bean.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(Value.class)){
                field.setAccessible(true);
                try {
                    field.set(bean,field.getAnnotation(Value.class).value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

        return bean;
    }
}
