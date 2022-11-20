package com.wzy.v2.provider;


import com.wzy.rpcv2.config.ProviderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Provider {

    public static void main(String[] args) {

        new AnnotationConfigApplicationContext(ProviderConfig.class).start();
    }
}
