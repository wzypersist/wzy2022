package com.wzy.v1.provider;

import com.wzy.rpcv1.provider.RpcServer;



public class Provider {

    public static void main(String[] args) {

        RpcServer proxyServer = new RpcServer();
        // 创建一个服务实例
        proxyServer.publisher(new TestServiceImpl(), 8080);
    }
}
