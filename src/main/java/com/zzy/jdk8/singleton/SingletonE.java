package com.zzy.jdk8.singleton;

public class SingletonE {
    public static SingletonE SINGLETON_E;
    private SingletonE(){}

    public static final synchronized SingletonE getSingletonE(){
        if (null == SINGLETON_E){
            SINGLETON_E = new SingletonE();
        }
        return SINGLETON_E;
    }
}