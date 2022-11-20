package com.wzy.v1.provider;


import com.wzy.v1.api.TestService;

import java.util.Random;

public class TestServiceImpl implements TestService {

    @Override
    public String test(String name) {
        System.out.println("new requst coming..." + name);

        Random random = new Random();
        String json = "{\"name\":" + "\"" + name + "\"" + ", \"age\":" + random.nextInt(40) + "}";
        return json;
    }
}
