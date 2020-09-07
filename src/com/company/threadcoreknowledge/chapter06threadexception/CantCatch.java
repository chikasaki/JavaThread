package com.company.threadcoreknowledge.chapter06threadexception;

public class CantCatch implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCatch()).start();
            Thread.sleep(300);
            new Thread(new CantCatch()).start();
            Thread.sleep(300);
            new Thread(new CantCatch()).start();
            Thread.sleep(300);
            new Thread(new CantCatch()).start();
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
