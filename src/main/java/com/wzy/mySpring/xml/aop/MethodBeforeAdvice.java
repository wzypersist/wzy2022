package com.wzy.mySpring.xml.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice{
    
    void before(Method method,Object[] args, Object object) throws Throwable;
    
}
