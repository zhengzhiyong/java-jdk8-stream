package com.zzy.jdk8.singleton;

public class SingletonG {
    public static SingletonG singletonG;
    private SingletonG(){}

    public static final SingletonG getSingletonG(){
        if (null == singletonG){
            synchronized (SingletonG.class){
                if (null == singletonG){
                    singletonG = new SingletonG();
                }
            }
        }
        return singletonG;
    }
}