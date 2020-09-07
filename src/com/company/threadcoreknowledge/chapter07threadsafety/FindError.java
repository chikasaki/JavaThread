package com.company.threadcoreknowledge.chapter07threadsafety;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class FindError implements Runnable{
    private static int index = 0;
    private static FindError instance = new FindError();

    private AtomicInteger realIndex = new AtomicInteger();
    private AtomicInteger wrongCount = new AtomicInteger();

    private CyclicBarrier cb1 = new CyclicBarrier(2);
    private CyclicBarrier cb2 = new CyclicBarrier(2);


    private boolean[] marked = new boolean[1000000];

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(FindError.index);
        System.out.println("right: " + instance.realIndex);
        System.out.println("wrong: " + instance.wrongCount);
    }

    @Override
    public void run() {
        for(int i = 0; i < 100000; i ++) {
            try {
                cb2.reset();
                cb1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index ++;
            try {
                cb1.reset();
                cb2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();

            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("wrong in " + i);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
