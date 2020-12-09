package com.zzy.jdk8.string;

import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    public void test1(){

        String str = "i";
        String str1 = new String("i");
        System.out.println(str == str1);
        System.out.println(str1.hashCode());
        str1 = str1 + "---";
        System.out.println(str1.hashCode());

        //线程安全的
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.hashCode());
        int stringbufferHashCode1 = stringBuffer.toString().hashCode();
        stringBuffer.append("str1");
        System.out.println(stringBuffer.hashCode());
        int stringbufferHashCode2 = stringBuffer.toString().hashCode();
        stringBuffer.append("str2");
        System.out.println(stringBuffer.hashCode());
        System.out.println("stringBuffer.toString().hashCode():"+(stringbufferHashCode1 == stringbufferHashCode2));

        //非线程安全的
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.hashCode());
        stringBuilder.append("str1");
        int stringBuilderhashCode1 = stringBuilder.toString().hashCode();
        System.out.println(stringBuilder.hashCode());
        stringBuilder.append("str2");
        int stringBuilderhashCode2 = stringBuilder.toString().hashCode();
        System.out.println(stringBuilder.hashCode());

        System.out.println(stringBuilder.equals(stringBuffer));
        System.out.println("stringBuilder.toString().hashCode():"+(stringBuilderhashCode1 == stringBuilderhashCode2));
        System.out.println(stringBuilder.reverse().toString().equals(stringBuffer.reverse().toString()));

        System.out.println(new StringC().getStr() == new StringC().getStr());
        System.out.println(new StringC().getName() == new StringC().getName());
        System.out.println(new StringC().getAddress() == new StringC().getAddress());
        System.out.println(new StringC().getSingletonA() == new StringC().getSingletonA());
    }

    @Test
    public void test2(){
        String str1="hello";
        String str2="he"+"llo";
        String str3="he"+new String("llo");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
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

