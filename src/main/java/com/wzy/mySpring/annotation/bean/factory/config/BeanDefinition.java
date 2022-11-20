package com.wzy.mySpring.annotation.bean.factory.config;

import com.wzy.mySpring.annotation.bean.PropertyValue;
import lombok.Data;

@Data
public class BeanDefinition {

    private static String SINGLETON = "singleton";

    private static String PROTOTYPE = "prototype";

    private Class type;

    private String scope = SINGLETON;

    private String initMethodName;

    private String destoryMethodName;

    private PropertyValue propertyValue;

    private boolean singleton = true;

    private boolean prototype = false;

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SINGLETON.equals(scope);
        this.prototype = PROTOTYPE.equals(scope);
    }

}
