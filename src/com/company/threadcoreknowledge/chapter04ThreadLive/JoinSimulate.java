package com.company.threadcoreknowledge.chapter04ThreadLive;

public class JoinSimulate {
    private static Object o = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("enter sub thread");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit sub thread");
            synchronized (o) {
                o.notify();
            }
        });
        thread.start();
        synchronized (o) {
            o.wait();
        }
        System.out.println("main end");
    }
}
