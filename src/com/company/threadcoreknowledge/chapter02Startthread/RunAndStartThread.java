package com.company.threadcoreknowledge.chapter02Startthread;

public class RunAndStartThread {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
