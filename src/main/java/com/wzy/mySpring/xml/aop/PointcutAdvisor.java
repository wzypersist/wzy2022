package com.wzy.mySpring.xml.aop;

public interface PointcutAdvisor extends Advisor{
    
    Pointcut getPointcut();
    
}
