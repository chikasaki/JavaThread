package com.company.threadcoreknowledge.chapter07threadsafety;

public class DeathLock implements Runnable{
    private int flag;
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] args) {
        DeathLock d1 = new DeathLock();
        DeathLock d2 = new DeathLock();

        d1.flag = 0;
        d2.flag = 1;
        Thread thread1 = new Thread(d1);
        Thread thread2 = new Thread(d2);

        thread1.start();
        thread2.start();

    }

    @Override
    public void run() {
        if(flag == 0) {
            synchronized (o1) {
                System.out.println("flag = " + flag);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(flag == 1) {
            synchronized (o2) {
                System.out.println("flag = " + flag);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
