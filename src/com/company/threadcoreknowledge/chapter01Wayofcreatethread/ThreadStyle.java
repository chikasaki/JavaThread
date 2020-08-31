package com.company.threadcoreknowledge.chapter01Wayofcreatethread;

public class ThreadStyle extends Thread{

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("create a new thread by extends Thread");
    }
}
