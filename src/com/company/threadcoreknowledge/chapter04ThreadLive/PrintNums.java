package com.company.threadcoreknowledge.chapter04ThreadLive;

public class PrintNums implements Runnable{
    private static Object o = new Object();
    private int start;

    public PrintNums(int start) {
        this.start = start;
    }

    @Override
    public void run() {
        for(int i = start; i < 100; i += 2) {
            synchronized (o) {
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), i);
                o.notify();
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintNums even = new PrintNums(0);
        PrintNums odd = new PrintNums(1);

        Thread thread1 = new Thread(even);
        Thread thread2 = new Thread(odd);

        thread1.start();
        Thread.sleep(10);
        thread2.start();
//        Thread.sleep(1000);
//        synchronized (o){
//            o.notifyAll();
//        }
    }
}
