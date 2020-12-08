package com.zzy.jdk8.singleton;

public class SingletonB {
    public static SingletonB SINGLETON_B;
    static {
        SINGLETON_B = new SingletonB();
    }
    private SingletonB(){}

    public static final SingletonB getSingletonB(){
        return SINGLETON_B;
    }
}