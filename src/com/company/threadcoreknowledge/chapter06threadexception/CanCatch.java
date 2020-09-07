package com.company.threadcoreknowledge.chapter06threadexception;

public class CanCatch implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

    private static class MyHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + "发生异常! err: " + e.getMessage());
        }
    }

    private static class MyHandler2 implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("HANDLER2");
            System.out.println(t.getName() + "发生异常! err: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.UncaughtExceptionHandler handler = new MyHandler();
        Thread.UncaughtExceptionHandler handler2 = new MyHandler2();
        Thread.setDefaultUncaughtExceptionHandler(handler);
        Thread thread = new Thread(new CanCatch());
        thread.setUncaughtExceptionHandler(handler2);
        thread.start();
    }
}
