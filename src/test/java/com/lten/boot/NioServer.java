package com.lten.boot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/6/18 11:11
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置非阻塞模式
        ssc.configureBlocking(false);
        //绑定端口号
        ssc.bind(new InetSocketAddress(9999));
        //创建多路复用选择器对象
        Selector selector = Selector.open();
        //注册accept事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //有事件就会触发
            selector.select();
            //获取事件的集合对象
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                if (sk.isAcceptable()) {
                    ServerSocketChannel ss = (ServerSocketChannel) sk.channel();
                    SocketChannel sc = null;
                    while (sc == null) {
                        sc = ss.accept();
                    }
                    //设置
                    sc.configureBlocking(false);
                    System.out.println("有客户端接入，负责处理的线程：" + Thread.currentThread().getId());
                    sc.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                } else if (sk.isReadable()) {
                    //获取 SocketChannel 对象
                    SocketChannel sc = (SocketChannel)sk.channel();
                    //创建缓冲区对象
                    ByteBuffer buf = ByteBuffer.allocate(10);
                    //执行读操作
                    sc.read(buf);
                    //输出读取到的内容
                    System.out.println("服务器端读取客户端发送的信息："    +new String(buf.array()));
                    //注销read事件
                    sc.register(selector, sk.interestOps()&~SelectionKey.OP_READ);
                } else if (sk.isWritable()) {
                    //获取 SocketChannel 对象
                    SocketChannel sc = (SocketChannel)sk.channel();
                    //创建缓冲区对象
                    ByteBuffer buf = ByteBuffer.wrap("over".getBytes());
                    //执行写的操作
                    sc.write(buf);
                    //注销write事件
                    sc.register(selector,sk.interestOps()&~SelectionKey .OP_WRITE);
                }
                iterator.remove();
            }
        }
    }

}
