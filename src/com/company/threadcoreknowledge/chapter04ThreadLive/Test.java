package com.company.threadcoreknowledge.chapter04ThreadLive;

public class Test {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            System.out.println("enter subthread");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(mainThread.getState());
            System.out.println("subthread end");
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}
