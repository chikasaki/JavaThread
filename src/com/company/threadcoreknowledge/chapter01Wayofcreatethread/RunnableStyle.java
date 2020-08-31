package com.company.threadcoreknowledge.chapter01Wayofcreatethread;

public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("create a new thread by implementing Runnable interface");
    }
}
