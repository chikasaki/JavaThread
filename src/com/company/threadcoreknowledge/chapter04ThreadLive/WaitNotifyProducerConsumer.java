package com.company.threadcoreknowledge.chapter04ThreadLive;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class WaitNotifyProducerConsumer {
    private static final int capacity = 10;
    private static Queue<Date> list = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Producer());
        Thread thread2 = new Thread(new Consumer());

        thread1.start();
        thread2.start();

        Thread.sleep(0);
    }

    private static class Producer implements Runnable{

        @Override
        public void run() {
            for(int i = 0; i < 100; i ++) {
                synchronized (list) {
                    if (list.size() == capacity) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Date date = new Date();
                    System.out.println("produce " + date);
                    list.add(date);
                    list.notify();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 100; i ++) {
                synchronized (list) {
                    if(list.isEmpty()) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("consume " + list.remove() + " at " + new Date());
                    list.notify();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
