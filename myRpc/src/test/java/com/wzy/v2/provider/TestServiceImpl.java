package com.wzy.v2.provider;

import com.wzy.rpcv2.annotation.RpcService;
import com.wzy.v2.api.TestService;


import java.util.Random;

@RpcService(TestService.class)
public class TestServiceImpl implements TestService {

    @Override
    public String test(String name) {
        System.out.println("new requst coming..." + name);

        Random random = new Random();
        String json = "{\"name\":" + "\"" + name + "\"" + ", \"age\":" + random.nextInt(40) + "}";
        return json;
    }
}
