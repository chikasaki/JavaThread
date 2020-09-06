package com.company.threadcoreknowledge.chapter04ThreadLive;

public class WaitingToBlocked {
    private static Object resourceA = new Object();
//    private static Object resourceB = new Object();
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " get resourceA lock");
                    try {
                        System.out.println(Thread.currentThread().getName() + " release resourceA lock");
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " get resourceA again");
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " get resourceA lock");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resourceA.notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        System.out.println(thread1.getState());
        Thread.sleep(100);
        System.out.println(thread1.getState());
        Thread.sleep(200);
        System.out.println(thread1.getState());
    }
}
