package com.wzy.mySpring.bean.factory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 返回指定类型的所有Bean
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String ,T> getBeansofType(Class<T> type);

    /**
     * 返回所有BeanName
     * @return
     */
    String[] getBeanNames();
}
