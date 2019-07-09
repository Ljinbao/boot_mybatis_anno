package com.lten.boot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Function;
import lombok.Data;
import lombok.ToString;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/6/17 16:22
 */
public class LambdaTest {
    public static void main(String[] args) {
   /*     Map<String, Double> map = new HashMap<>();
        map.put("上网-上不了网", 0.9185158610343933);
        map.put("时间-有", 0.8327374458312988);
        map.put("周边-只有自己", 0.1289435774087906);
        map.put("周边-不清楚", 0.312673956155777);
        map.put("位置-有地名", 0.9792110323905945);
        List<Map<String, Double>> list = new ArrayList<>();
        list.add(map);
        List<Map<String, Object>> result = new ArrayList<>();
*//*
        Map<String, List<Map<String, Object>>> glist = list.stream().collect(Collectors.groupingBy(e -> e.get("name").toString()));

        glist.forEach((k, slist) -> {
            Optional<Map<String, Object>> max = slist.stream().max((o1, o2) -> {
                Integer price1 = (Integer) o1.get("price");
                Integer price2 = (Integer) o2.get("price");
                return price1.compareTo(price2);
            });
            result.add(max.get());
        });
        System.out.println("--------testMerge-------------");
        result.forEach(x -> {
            System.out.println(x);
        });*//*


        //通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，
        //或者需要前后元素间维持着某种状态信息的 Stream。把 Supplier 实例传递给 Stream.generate() 生成的 Stream，
        //默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。
        //由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小。
        Random seed = new Random();
        Supplier supplier = seed::nextInt;

        Stream.generate(supplier).limit(10).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(System.out::println);


        Stream.generate(new Person1Supplier()).limit(10).forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));

        Map<Integer, List<Person1>> collect = Stream.generate(new Person1Supplier()).limit(100).collect(Collectors.groupingBy(Person1::getAge));
        Iterator<Map.Entry<Integer, List<Person1>>> iterator = collect.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, List<Person>> persons = (Map.Entry) iterator.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
        }

        Map<Boolean, List<Person1>> children = Stream.generate(new Person1Supplier()).
                limit(100).collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());*/


        List<Person1> list11 = Arrays.asList(new Person1(1,"li",18),new Person1(1,"li",19),new Person1(2,"wang",20),new Person1(3,"zhang",19));
        Map<String, Person1> map1 = list11.stream().collect(Collectors.toMap(Person1::getName, person1 -> person1,(v1,v2) -> v2));
        map1.forEach((v1,v2) -> System.out.println("Key: " + v1 +", value: "+ v2));
        String[] ss = {"data","byg","ggg"};
        String s = JSON.toJSONString(ss);
        System.out.println(s);
        JSONArray a = JSON.parseArray(s);
        System.out.println(a.get(0));
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        //System.out.println(a.get(3));//throws IOE

        List<String> stringList = Arrays.asList("a=0","b=1","c=2","d=3","e=4","f=5","g=6","h=7","i=8","j=9","k=10","l=11","m=12");
        Map<String, String> collect = stringList.stream().collect(Collectors.toMap(x -> (x.split("="))[0], v -> (v.split("="))[1], (v1, v2) -> v2));
        System.out.println(JSON.toJSONString(collect));

    }

}

class Person1Supplier implements Supplier<Person1> {
    private int index = 0;
    private Random random = new Random();
    @Override
    public Person1 get() {
        return new Person1(index++, "StormTestUser" + index, random.nextInt(100));
    }
}

@Data
@ToString
class Person1{
    private int no;
    private String name;
    private int age;

    public Person1(int no,String name,int age){
        this.no = no;
        this.name = name;
        this.age = age;
    }
}
