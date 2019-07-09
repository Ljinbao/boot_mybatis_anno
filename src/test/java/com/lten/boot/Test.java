package com.lten.boot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0
 * @date 2019/5/21 13:38
 */
public class Test {

    @org.junit.Test
    public void testAccept() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置非阻塞模式
        ssc.configureBlocking(false);
        //绑定端口
        ssc.bind(new InetSocketAddress(9999));
        SocketChannel sc = null;
        while (sc == null) {
            sc = ssc.accept();
        }
        System.out.println("有客户端连入");
        ByteBuffer buf = ByteBuffer.allocate(1024);
        sc.configureBlocking(false);
        int read = sc.read(buf);
        System.out.println("位置" + read);
        System.out.println("服务器端读取到内容 " + new String(buf.array()));
        buf.clear();

    }

    @org.junit.Test
    public void testConn() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("127.0.0.1", 9999));
        System.out.println("连接成功");
        while (!sc.isConnected()) {
            sc.finishConnect();
        }
        ByteBuffer buf = ByteBuffer.wrap(("helloworld").getBytes());
        sc.write(buf);
        System.out.println("客户端写完了内容");

    }

    @org.junit.Test
    public void testConn1() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("127.0.0.1", 9999));
        System.out.println("连接成功");
        while (!sc.isConnected()) {
            sc.finishConnect();
        }
        ByteBuffer buf = ByteBuffer.wrap(("helloworld11111").getBytes());
        sc.write(buf);
        System.out.println("客户端写完了内容");
    }

    @org.junit.Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "4");
        map.put("2", "3");
        map.put("3", "2");
        map.put("4", "1");

        map.remove("1");
        map.remove("1");
        map.remove("1");
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("C", "C++", "Java", "Python", "Scala", "PHP", "C#");
        //Stream类中的方法使用
        //filter
        list.stream().filter(string -> string.length() > 3).forEach(System.out::println);
        //map
        list.stream().map(string -> string + "fuck").forEach(System.out::println);
        //limit/skip
        list.stream().limit(3).forEach(System.out::println);
        list.stream().skip(4).forEach(System.out::println);
        //sorted
        list.stream().sorted().forEach(System.out::println);
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //distinct
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().distinct().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
        strings.stream().filter(string -> string.length() <= 6).forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        strings.stream().filter(string -> string.length() <= 6).map(String::length).forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        strings.stream().filter(string -> string.length() <= 6).map(String::length).sorted().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        strings.stream().filter(string -> string.length() <= 6).map(String::length).sorted().limit(3).forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        strings.stream().filter(string -> string.length() <= 6).map(String::length).sorted().limit(3).distinct().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        List<Person> plist = Arrays.asList(new Person(1, "zhangsan"), new Person(2, "lisi"), new Person(3, "wangwu"), new Person(4, "qianliu"), new Person(5, "sunba"));
        Map<String, Person> collect = plist.stream().collect(Collectors.toMap(Person::getName, person -> person));
        collect.entrySet().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        collect.put("zhangsan", new Person(1, "zhangsan"));
        collect.entrySet().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");
        Map<Integer, String> collect1 = plist.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        collect1.entrySet().forEach(System.out::println);
        collect1.put(1, "ssss");
        collect1.entrySet().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------------------------------");

        //

        Map<String, String> map = new HashMap<>();
        map.put("", "");
        map.put("", "");
        map.put("", "");
        map.put("", "");
        map.put("", "");
        //map转list


    }
}

@Data
@AllArgsConstructor
class Person {
    private int id;
    private String name;
}
