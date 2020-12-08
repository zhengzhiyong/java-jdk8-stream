package com.zzy.jdk8.collections;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class HashSetTest {
    @Test
    public void test1(){
        Set<String> set = new HashSet<>();
        IntStream.rangeClosed(1,20).forEach(i->  set.add(String.valueOf(i)) );
        set.forEach(System.out::println);
    }
    @Test
    public void test2(){
        Set<String> set = new LinkedHashSet<>();
        IntStream.rangeClosed(1,20).forEach(i->  set.add(String.valueOf(i)) );
        set.forEach(System.out::println);
    }
}
