package com.zzy.jdk8.atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    @Test
    public void test1(){
        AtomicInteger integer = new AtomicInteger(1111);
        integer.addAndGet(1111);
        System.out.println(integer.get());
    }
}
