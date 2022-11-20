package com.wzy.rpcv2.config;

import com.wzy.rpcv2.provider.RpcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.wzy")
public class ProviderConfig {
    @Bean(name = "myRpcServer")
    public RpcServer myRpcServer(){return new RpcServer(8080);}
}
