package com.wzy.mySpring.bean.factory;

import java.util.HashMap;
import java.util.Map;

public interface BeanFactory {

    /**
     * 根据beanName获取
     * @param name
     */
    void getBean(String name);

    /**
     * 根据beanName和类型获取
     * @param name
     * @param type
     * @param <T>
     * @return
     */
    <T> T getBean(String name,Class<T> type);

    /**
     * 根据类型获取
     * @param type
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> type);

    /**
     * 是否包含Bean
     * @param name
     * @return
     */
    boolean containsBean(String name);



}
