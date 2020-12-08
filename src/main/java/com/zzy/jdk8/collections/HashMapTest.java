package com.zzy.jdk8.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class HashMapTest {
    @Test
    public void test1(){
        System.out.println("================start=================");
        Map<Long,String> longStringMap = new HashMap<>();
        for (int i = 0;i<11;i++){
            longStringMap.put((long)i,String.valueOf(i));
        }
        System.out.println("================end=================");
        LongStream.rangeClosed(1L,30L)
                .forEach(l->{
                    longStringMap.put(l,String.valueOf(l));
                });
        System.out.println((int)Math.pow(2,30) == 1 << 30);
        System.out.println(("hashMap的最大容量："+ (1 << 30)));

        System.out.println((16 << 1) + "     " + ((int)(0.75 * 16) << 1));
    }
}
