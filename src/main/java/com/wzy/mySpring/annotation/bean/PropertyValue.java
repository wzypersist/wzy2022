package com.wzy.mySpring.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class PropertyValue {

    private final String name;

    private final Object value;

}
