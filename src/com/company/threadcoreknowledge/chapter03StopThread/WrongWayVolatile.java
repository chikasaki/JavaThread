package com.company.threadcoreknowledge.chapter03StopThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WrongWayVolatile {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread thread = new Thread(producer);
        thread.start();
        Thread.sleep(1000);
        Thread.currentThread().isInterrupted()

        Consumer consumer = new Consumer();
        while (consumer.needMore()){
            System.out.println("消费数据:" + storage.take());
            Thread.sleep(100);
        }
        thread.interrupt();
    }
}

class Producer implements Runnable{
    private BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (true) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                    storage.put(num);
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者停止生产数据");
        }
    }
}

class Consumer {
    public boolean needMore() {
        return Math.random() < 0.95;
    }
}
