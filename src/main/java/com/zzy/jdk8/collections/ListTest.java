package com.zzy.jdk8.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ListTest {

    @Test
    public void test1(){
        List<String> stringList = new ArrayList<>();
        IntStream.rangeClosed(0,12).forEach(i-> {
            stringList.add("str"+i);
        });
        System.out.println(stringList);
        /**
         * 1、new ArrayList()的时候，数组大小为0
         * 2、第一次调用add方法的时候会初始化list的大小为10
         * 3、非第一次调用add方法的时候会和数组当前的大小作比较，如果数组大小已经满了，则处罚扩容机制
         * 4、扩容后的数组为原来的1.5倍，newLength = length + length >> 1; 如果length 为偶数，则newLength = 1.5 * length,否则 newLength = length + length/2;
         */
        int i = 0;
        System.out.println("初始化数组new ArrayList的时候，数组大小为:"+i);
        i = 10;
        System.out.println("第1次调用add方法的时候会初始化list的大小为:"+i);
        for (int k=2;k<10;k++){
            int m = i;
            int n = i >> 1;
            int j = m + n;
            i = j;
            System.out.println("第"+k+"次调用add方法的时候会初始化list的大小为:"+i);
        }
    }

    @Test
    public void test2(){
        List<String> stringList = new LinkedList<>();
        IntStream.rangeClosed(0,12).forEach(i-> {
            stringList.add("str"+i);
        });

        stringList.forEach(str->{
            System.out.println(str);
        });

    }
}
