package com.company.threadcoreknowledge.chapter08jmm;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryBarrier {
    private static int a, b, c, d;
    private static CountDownLatch cdl = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
//            try {
//                cdl.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            a = 1;
            b = c;
        });
        Thread thread2 = new Thread(() -> {
            c = 3;
            d = a;
//            cdl.countDown();
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("a=%d, b=%d, c=%d, d=%d", a, b, c, d);
    }
}
