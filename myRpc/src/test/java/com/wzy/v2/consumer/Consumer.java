package com.wzy.v2.consumer;


import com.wzy.rpcv2.config.ConsumerConfig;
import com.wzy.rpcv2.consumer.RpcProxyClient;
import com.wzy.v2.api.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Consumer {

    public static void main(String[] args) {
        /**
         * 与v1相比，区别是通过IOC容器获取代理Bean
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfig.class);
        RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);

        TestService service = rpcProxyClient.clientProxy(TestService.class, "localhost", 8080);
        String json = service.test("张三");
        System.out.println(json);
    }
}
