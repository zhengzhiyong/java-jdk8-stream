package com.zzy.jdk8.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class HashMapTest {
    @Test
    public void test1(){
        System.out.println("================start=================");
        Map<Long,String> longStringMap = new HashMap<>();
        int hashCode1 = longStringMap.hashCode();
        for (int i = 0;i<11;i++){
            longStringMap.put((long)i,String.valueOf(i));
        }
        int hashCode2 = longStringMap.hashCode();
        System.out.println(hashCode1 == hashCode2);
        int n =longStringMap.size();

        for (int i = 0;i<n;i++){
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

    @Test
    public void test2(){
        Map<String,String> linkedHashMap = new LinkedHashMap<>();
        IntStream.rangeClosed(1,20).forEach(i->  linkedHashMap.put(String.valueOf(i),String.valueOf(i)) );
        linkedHashMap.forEach((k,v)-> System.out.println("K:"+k + "   V:"+v));
    }
    @Test
    public void test3(){
        Map<String,String> hashMap = new HashMap<>();
        IntStream.rangeClosed(1,20).forEach(i->  hashMap.put(String.valueOf(i),String.valueOf(i)) );
        hashMap.forEach((k,v)-> System.out.println("K:"+k + "   V:"+v));
    }
}
