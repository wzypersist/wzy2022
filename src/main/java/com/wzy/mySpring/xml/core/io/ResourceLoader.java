package com.wzy.mySpring.xml.core.io;

public interface ResourceLoader {
    
    String CLASSPATH_URL_PREFIX = "classpath:";
    
    Resource getReSource(String location);
    
}
