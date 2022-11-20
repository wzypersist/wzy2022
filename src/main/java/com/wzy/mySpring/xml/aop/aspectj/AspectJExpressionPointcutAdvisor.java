package com.wzy.mySpring.xml.aop.aspectj;

import com.wzy.mySpring.xml.aop.Pointcut;
import com.wzy.mySpring.xml.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    
    //切面
    private AspectJExpressionPointcut pointcut;
    
    // 具体拦截方法
    private Advice advice;
    
    //表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if(pointcut == null){
              pointcut = new AspectJExpressionPointcut(expression);
              return pointcut;
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
