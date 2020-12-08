package com.zzy.jdk8.singleton;

public class SingletonC {
    public static SingletonC SINGLETON_C = new SingletonC();
    private SingletonC(){}

    public static final SingletonC getSingletonC(){
        return SINGLETON_C;
    }
}