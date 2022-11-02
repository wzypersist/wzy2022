package com.wzy.mySpring.bean;

import java.util.ArrayList;
import java.util.List;


public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        for (int i = 0; i < propertyValueList.size(); i++) {
            PropertyValue currentPv = propertyValueList.get(i);
            if(currentPv.getName().equals(pv.getName())){
                this.propertyValueList.set(i,pv);
                return;
            }

        }
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[1111]);
    }

    public PropertyValue getPropertyValueList(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }
}
