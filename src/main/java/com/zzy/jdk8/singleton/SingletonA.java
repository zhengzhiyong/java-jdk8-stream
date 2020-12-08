package com.zzy.jdk8.singleton;

public class SingletonA {
    private static class SingletonAHolder{
         public static final SingletonA SINGLETON_A = new SingletonA();
    }
    private SingletonA(){}

    public static final SingletonA getSingletonA(){
        return SingletonAHolder.SINGLETON_A;
    }
}
