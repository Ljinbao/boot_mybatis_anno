package com.lten.boot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/6/17 15:41
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("127.0.0.1", 9999));
        System.out.println("客户端连入");
        while (!sc.isConnected()) {
            sc.finishConnect();
        }
        ByteBuffer buffer = ByteBuffer.wrap("helloworld".getBytes());
        sc.write(buffer);
        //创建一个空的缓冲区
        ByteBuffer rbuf = ByteBuffer.allocate(4); //执行读操作
        sc.read(rbuf); System.out.println("客户端读取到服务器端的数据："+ new String(rbuf.array()));
        while (true) ;
    }
}
