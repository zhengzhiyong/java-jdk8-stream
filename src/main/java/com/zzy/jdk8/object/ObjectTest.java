package com.zzy.jdk8.object;

import org.junit.jupiter.api.Test;

public class ObjectTest {
    @Test
    public void test1(){
        Person person = new Person(1L,"张三");
        System.out.println(person.toString());
        person.setName("张三1");
        System.out.println(person.toString());



        Student student = Student.builder().id(1L).name("aaa").build();
        System.out.println(student.hashCode());
        student.setId(2L);
        System.out.println(student.hashCode());
        person.setName("张三1");
        System.out.println(student.hashCode());
    }
}
