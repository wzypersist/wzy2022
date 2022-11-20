package com.wzy.rpcv2.config;

import com.wzy.rpcv2.consumer.RpcProxyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    @Bean(name = "rpcProxyClient")
    public RpcProxyClient proxyClient(){return new RpcProxyClient();}
}
