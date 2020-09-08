package com.company.threadcoreknowledge.chapter09singleton;

/**
 * 懒汉式写法 -- 双重锁，效率高
 */
public class Singleton3 {

    private volatile static Singleton3 INSTANCE;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton3.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
