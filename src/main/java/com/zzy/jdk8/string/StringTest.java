package com.zzy.jdk8.string;

import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    public void test1(){

        String str = "i";
        String str1 = new String("i");
        System.out.println(str == str1);

        //线程安全的
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.hashCode());
        stringBuffer.append("str1");
        System.out.println(stringBuffer.hashCode());
        stringBuffer.append("str2");
        System.out.println(stringBuffer.hashCode());

        //非线程安全的
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.hashCode());
        stringBuilder.append("str1");
        System.out.println(stringBuilder.hashCode());
        stringBuilder.append("str2");
        System.out.println(stringBuilder.hashCode());

        System.out.println(stringBuilder.equals(stringBuffer));
        System.out.println(stringBuilder.reverse().toString().equals(stringBuffer.reverse().toString()));

        System.out.println(new StringC().getStr() == new StringC().getStr());
        System.out.println(new StringC().getName() == new StringC().getName());
        System.out.println(new StringC().getAddress() == new StringC().getAddress());
        System.out.println(new StringC().getSingletonA() == new StringC().getSingletonA());
    }


    public final class StringC{
        public static final int NUMBER = 10;
        public static final String STR = "AAA";
        public int getNumber(){
            return NUMBER;
        }
        public String getStr(){
            return STR;
        }
        public String getName(){
            return "AAA";
        }
        public String getAddress(){
            return new String(STR);
        }
        public SingletonA getSingletonA(){
            return SingletonA.getA();
        }
    }
}

