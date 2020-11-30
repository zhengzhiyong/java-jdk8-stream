package com.zzy.jdk8.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName(value = "测试类")
public class Test1 {
    List<Person> personList = new ArrayList<Person>();

    @BeforeEach
    @DisplayName(value = "初始化方法")
    void init() {
        System.out.println("初始化方法run");
        personList.add(new Person("Tom", 8900, 30, "male", "New York"));
        personList.add(new Person("Jack", 7000, 28, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 28, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 25, "female", "New York"));
        personList.add(new Person("Owen", 9500, 22, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 24, "female", "New York"));
    }

    @Test
    @DisplayName(value = "测试方法一")
    public void test1() {
        List<String> listString = Arrays.asList("a", "b", "c");

        listString.forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "测试方法二")
    public void test2() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream.forEach(System.out::println);
        System.out.println("=====================");
        stream2.forEach(System.out::println);
        System.out.println("=====================");
        stream3.forEach(System.out::println);
    }

    @Test
    @DisplayName(value = "测试方法三")
    public void test3() {
        Integer age = null;
        boolean result = Optional.ofNullable(age).isPresent();
        System.out.println(result);
    }

    @Test
    @DisplayName(value = "测试方法四")
    public void test4() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        boolean countFlag = list.stream().filter(x -> x > 6).count() > 0;
        long count6 = list.stream().filter(x -> x > 6).count();
        System.out.println("大于6的数字共有" + count6 + "个");
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
        System.out.println("是否存在大于6的值：" + countFlag);
        list.stream().filter(x -> x > 7).forEach(System.err::println);
    }

    @Test
    @DisplayName(value = "测试方法五")
    public void test5() {
        personList.stream().filter(person -> person.getSalary() > 8000).map(Person::getName).collect(Collectors.toList()).forEach(System.out::println);
        Optional<Person> maxPerson = personList.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println("最高工资是：" + maxPerson.get().getSalary());
    }

    @Test
    @DisplayName(value = "测试方法六")
    public void test6() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 4);
        Optional<Integer> maxInteger = intList.stream().max(Comparator.comparing(Integer::intValue));
        Optional<Integer> maxInteger2 = intList.stream().max(Integer::compareTo);
        Optional<Integer> maxInteger3 = intList.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        Optional<Integer> maxInteger4 = intList.stream().max((o1, o2) -> o1.compareTo(o2));
        System.out.println("最长的字符串：" + max.get());
        System.out.println(maxInteger.get());
        System.out.println(maxInteger2.get());
        System.out.println(maxInteger3.get());
        System.out.println(maxInteger4.get());
    }

    @Test
    @DisplayName(value = "测试方法七")
    public void test7() {
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);
    }

    @Test
    @DisplayName(value = "测试方法八")
    public void test8() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    @Test
    @DisplayName(value = "测试方法九")
    public void test9() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
    }

    @Test
    @DisplayName(value = "测试方法十")
    public void test10() {
        // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
                (sum1, sum2) -> sum1 + sum2);
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);
        // 求最高工资方式1：
        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);
        // 求最高工资方式2：
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1, max2) -> max1 > max2 ? max1 : max2);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);
        System.out.println("工资之和：" + personList.stream().mapToInt(Person::getSalary).sum());
        System.out.println("最高工资：" + personList.stream().mapToInt(Person::getSalary).max().getAsInt());
    }

    @Test
    @DisplayName(value = "测试方法十一")
    public void test11() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
  }

  @Test
  @DisplayName(value = "测试方法十二")
  public void test12(){
      // 求总数
      Long count = personList.stream().collect(Collectors.counting());
      // 求平均工资
      Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
      // 求最高工资
      Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
      // 求工资之和
      Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
      // 一次性统计所有信息
      DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

      System.out.println("员工总数：" + count);
      System.out.println("员工平均工资：" + average);
      System.out.println("员工工资总和：" + sum);
      System.out.println("员工工资所有统计：" + collect);
      System.out.println("员工工资最高工资：" + max.get());
  }

  @Test
  @DisplayName(value = "测试方法十三")
  public void test13(){
      // 将员工按薪资是否高于8000分组
      Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
      // 将员工按性别分组
      Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
      // 将员工先按性别分组，再按地区分组
      Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
      System.out.println("员工按薪资是否大于8000分组情况：" + part);
      System.out.println("员工按性别分组情况：" + group);
      System.out.println("员工按性别、地区：" + group2);
  }

    @Test
    @DisplayName(value = "测试方法十四")
    public void test14(){
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    @Test
    @DisplayName(value = "测试方法十五")
    public void test15(){
        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);
        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    @Test
    @DisplayName(value = "测试方法十六")
    public void test16(){
        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    @Test
    @DisplayName(value = "测试方法十七")
    public void test17(){
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(10).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }
}
