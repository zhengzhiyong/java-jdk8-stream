package com.zzy.jdk8.string;

public class SingletonA {
    private static class SingletonAHolder{
        private static final SingletonA A = new SingletonA();
    }
    private SingletonA(){}
    public static final SingletonA getA(){
        return SingletonAHolder.A;
    }
}
