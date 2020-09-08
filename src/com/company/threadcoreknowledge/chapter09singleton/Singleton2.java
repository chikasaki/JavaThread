package com.company.threadcoreknowledge.chapter09singleton;

/**
 * 懒汉式加载 -- 线程安全，但效率比较低
 */
public class Singleton2 {

    private static Singleton2 INSTANCE;

    private Singleton2() {

    }

    public synchronized static Singleton2 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}
