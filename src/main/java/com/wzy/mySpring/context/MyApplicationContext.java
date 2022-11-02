package com.wzy.mySpring.context;

import com.wzy.mySpring.bean.factory.config.BeanPostProcessor;
import com.wzy.mySpring.context.annotation.Autowired;
import com.wzy.mySpring.context.annotation.Component;
import com.wzy.mySpring.context.annotation.ComponentScan;
import com.wzy.mySpring.context.annotation.Scope;
import com.wzy.mySpring.bean.factory.config.BeanDefinition;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class MyApplicationContext {

    private Class configClass;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    private Map<String,Object> singletonObjects = new HashMap<>();

    private Map<String,Object> secondCache = new HashMap<>();

    public MyApplicationContext(Class configClass) {

        this.configClass = configClass;

        prepareBeanFactory();

        scan(configClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            Object bean = doCreateBean(beanName, beanDefinition);
            singletonObjects.put(beanName,bean);
        }

    }

    private void prepareBeanFactory() {

        ValueBeanPostProcessor valueBeanPostProcessor = new ValueBeanPostProcessor();
        beanPostProcessorList.add(valueBeanPostProcessor);

    }

    /**
     * 创建Bean
     * 1.实例化
     * 2.对有Autowired注解的属性赋值
     * 3.执行BeanPostProcessor的postProcessBeforeInitialization初始化前方法
     * 4.执行InitializingBean的afterPropertiesSet初始化方法
     * 5.执行BeanPostProcessor的postProcessAfterInitialization初始化后方法
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getType();
        Object instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
            secondCache.put(beanName,instance);
            for (Field field : clazz.getDeclaredFields()) {
                if(field.isAnnotationPresent(Autowired.class)){
                    field.setAccessible(true);
                    field.set(instance,getBean(field.getName()));
                }
                for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                    instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

                }
                if(instance instanceof InitializingBean){
                    ((InitializingBean) instance).afterPropertiesSet();
                }
                for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                    instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);

                }


            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;

    }

    /**
     * 1.如果为单例Bean,直接从singletonObjects中拿
     * 2.若为原型Bean，直接创建
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {

        if(!beanDefinitionMap.containsKey(beanName)){
            throw new NullPointerException();
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition.getScope().equals("singleton")){
            if(secondCache.get(beanName) != null){
                return secondCache.get(beanName);
            }
            Object singletonBean = singletonObjects.get(beanName);
            if(singletonBean == null){
                singletonBean = doCreateBean(beanName, beanDefinition);
                singletonObjects.put(beanName,singletonBean);
            }
            return singletonBean;
        }else{
            return doCreateBean(beanName, beanDefinition);
        }

    }

    /**
     * spring扫描
     * 1.首先对ComponentScan注解进行扫描，利用类加载器加载ComponentScan中的值
     * 2.扫描到的每个类进行Component判断，有的话添加到beanDefinitionmap中，其中，key为类名，value为beanDefinition
     * 3.对每个类是否实现BeanPostProcessor接口进行判断，如果有，将其添加到beanPostProcessorList中
     * @param configClass
     */
    private void scan(Class configClass) {

        if(configClass.isAnnotationPresent(ComponentScan.class)){
            ComponentScan componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScan.value();
            path = path.replace(".", "/");
            ClassLoader classLoader = MyApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if(file.isDirectory()){
                for (File f : file.listFiles()) {
                    String absolutePath = f.getAbsolutePath();
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\",".");

                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        if(clazz.isAnnotationPresent(Component.class)){

                            if(BeanPostProcessor.class.isAssignableFrom(clazz)){
                                BeanPostProcessor instance = (BeanPostProcessor) clazz.getConstructor().newInstance();
                                beanPostProcessorList.add(instance);
                            }

                            Component component = clazz.getAnnotation(Component.class);
                            String beanName = component.value();
                            if("".equals(beanName)){
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);
                            if(clazz.isAnnotationPresent(Scope.class)){
                                Scope scope = clazz.getAnnotation(Scope.class);
                                beanDefinition.setScope(scope.value());
                            }else{
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName,beanDefinition);

                        }

                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
