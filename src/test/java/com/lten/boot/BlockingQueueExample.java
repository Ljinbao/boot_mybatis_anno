package com.lten.boot;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Thoms
 * @version 1.0
 * @Description
 * @date 2019/6/10 11:41
 */
public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}

class Producer implements Runnable {

    protected BlockingQueue blockingQueue = null;

    Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
               System.out.println("生产第" + i + "个");
               blockingQueue.put(i);
               Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    protected BlockingQueue blockingQueue = null;

    Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("消费" + blockingQueue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
