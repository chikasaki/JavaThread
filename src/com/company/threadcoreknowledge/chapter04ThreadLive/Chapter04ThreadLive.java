package com.company.threadcoreknowledge.chapter04ThreadLive;

/**
 * 演示线程状态---Waiting，Timed_Waiting，Blocked
 */
public class Chapter04ThreadLive implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Chapter04ThreadLive();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        Thread.sleep(100);
        thread2.start();

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        Thread.sleep(1300);
//        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        try {
            sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sync() throws InterruptedException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait();
    }
}
