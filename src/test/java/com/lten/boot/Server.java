package com.lten.boot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/6/17 15:25
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9999));
        while(true) {
            Socket socket = server.accept();
            new Thread(new ClientRunner(socket)).start();
        }
    }
}

class ClientRunner implements Runnable {

    private Socket socket;

    public ClientRunner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("当前线程的ID：" + Thread.currentThread().getId());
    }
}

