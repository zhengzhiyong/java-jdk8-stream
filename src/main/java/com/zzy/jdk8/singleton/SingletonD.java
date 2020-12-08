package com.zzy.jdk8.singleton;

public class SingletonD {
    public static SingletonD SINGLETON_D;
    private SingletonD(){}

    public static final SingletonD getSingletonD(){
        if (null == SINGLETON_D){
            SINGLETON_D = new SingletonD();
        }
        return SINGLETON_D;
    }
}