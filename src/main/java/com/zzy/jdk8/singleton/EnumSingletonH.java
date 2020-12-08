package com.zzy.jdk8.singleton;

public enum EnumSingletonH {
    SINGLETONH;
    private EnumSingletonH(){}

    public static EnumSingletonH getSingletonH(){
        return SINGLETONH;
    }
}