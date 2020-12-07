package com.zzy.jdk8.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class HashMapTest {
    @Test
    public void test1(){
        Map<Long,String> longStringMap = new HashMap<>();
        LongStream.rangeClosed(1L,11L)
                .forEach(l->{
                    longStringMap.put(l,String.valueOf(l));
                });
        System.out.println(longStringMap);
        System.out.println((int)Math.pow(2,30) == 1 << 30);
        System.out.println(("hashMap的最大容量："+ (1 << 30)));
    }
}
