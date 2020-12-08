package com.zzy.jdk8.singleton;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingletonTest {

    @Test
    public void test1() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonA-hashcode：" + SingletonA.getSingletonA().hashCode());
        });
    }

    @Test
    public void test2() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonB-hashcode：" + SingletonB.getSingletonB().hashCode());
        });
    }


    @Test
    public void test3() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonC-hashcode：" + SingletonC.getSingletonC().hashCode());
        });
    }


    @Test
    public void test4() {
        //线程不安全
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonD-hashcode：" + SingletonD.getSingletonD().hashCode());
        });
    }


    @Test
    public void test5() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonE-hashcode：" + SingletonE.getSingletonE().hashCode());
        });
    }


    @Test
    public void test6() {
        //线程不安全
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonF-hashcode：" + SingletonF.getSingletonF().hashCode());
        });
    }

    @Test
    public void test7() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("SingletonG-hashcode：" + SingletonG.getSingletonG().hashCode());
        });
    }

    @Test
    public void test8() {
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            System.out.println("EnumSingletonH-hashcode：" + EnumSingletonH.getSingletonH().hashCode());
        });
    }

    @Test
    public void test9() {
        Set<SingletonPo> singletonSet = new HashSet<>(1000);
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonA.getSingletonA().hashCode();
            String key = SingletonA.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonB.getSingletonB().hashCode();
            String key = SingletonB.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonC.getSingletonC().hashCode();
            String key = SingletonC.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        //线程不安全
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonD.getSingletonD().hashCode();
            String key = SingletonD.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonE.getSingletonE().hashCode();
            String key = SingletonE.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        //线程不安全
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonF.getSingletonF().hashCode();
            String key = SingletonF.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = SingletonG.getSingletonG().hashCode();
            String key = SingletonG.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            int hashCode = EnumSingletonH.getSingletonH().hashCode();
            String key = EnumSingletonH.class.getSimpleName();
            singletonSet.add(new SingletonPo(hashCode,key));
        });
        singletonSet.forEach(System.out::println);

        Map<String,List<SingletonPo>> map = singletonSet.stream().collect(Collectors.groupingBy(SingletonPo::getName));
        map.forEach((k,v)->{
            System.out.println(k + "线程"+ (v.size() == 1 ? "" :"不")+"安全");
        });
    }

    class SingletonPo{
        private int hash;
        private String name;

        private boolean equalsStr(String str1, String str2){
            if(StringUtils.isBlank(str1) && StringUtils.isBlank(str2)){
                return true;
            }
            if(!StringUtils.isBlank(str1) && str1.equals(str2)){
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object object) {
            if(this == object){
                return true;//地址相等
            }
            if(object == null){
                return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
            }
            if(!(object instanceof SingletonPo)){
                return false;
            }
            SingletonPo other = (SingletonPo) object;
            //需要比较的字段相等，则这两个对象相等
            return equalsStr(this.name, other.name) && this.hash == other.hash;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (this.name == null ? 0 : name.hashCode());
            result = 31 * result + (this.hash);
            return result;
        }

        @Override
        public String toString() {
            return "[name:"+ this.name+",    hash:"+this.hash+",   hashCode:"+hashCode()+"]";
        }

        public SingletonPo(int hashCode, String name) {
            this.hash = hashCode;
            this.name = name;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
