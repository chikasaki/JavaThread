package com.company.threadcoreknowledge.chapter09singleton;

/**
 * 懒汉式 -- 静态内部类
 */
public class Singleton4 {

    private Singleton4() {

    }

    private static class Innerclass{
        public static Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return Innerclass.INSTANCE;
    }
}
