package com.company.threadcoreknowledge.chapter08jmm;

public class Test {

    private int a = 1;
    private int b = 2;

    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.println("b = " + b + ", a = " + a);
    }


    public static void main(String[] args) throws InterruptedException {
        while(true) {
            Test test = new Test();
            Thread thread1 = new Thread(() -> {
                test.change();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread thread2 = new Thread(() -> {
                test.print();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        }
    }
}
