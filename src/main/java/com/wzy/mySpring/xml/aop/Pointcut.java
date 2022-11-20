package com.wzy.mySpring.xml.aop;

public interface Pointcut {
    
    ClassFilter getClassFilter();
    
    MethodMatcher getMethodMatcher();
    
}
