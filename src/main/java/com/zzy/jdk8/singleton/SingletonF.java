package com.zzy.jdk8.singleton;

public class SingletonF {
    public static SingletonF singletonF;
    private SingletonF(){}

    public static final SingletonF getSingletonF(){
        if (null == singletonF){
            synchronized (SingletonF.class){
                singletonF = new SingletonF();
            }
        }
        return singletonF;
    }
}