package com.company.threadcoreknowledge.chapter10deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class TransferMoney implements Runnable{

    private int flag;
    private static Account a = new Account(500);
    private static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney m1 = new TransferMoney();
        TransferMoney m2 = new TransferMoney();
        m1.flag = 0;
        m2.flag = 1;

        Thread thread1 = new Thread(m1);
        Thread thread2 = new Thread(m2);

        thread1.start();
        thread2.start();

//        thread1.join();
//        thread2.join();

        Thread.sleep(1000);
        //使用ThreadMXBean发现问题并解决问题
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = bean.findDeadlockedThreads();
        if(deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = bean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("发现死锁: " + threadInfo.getThreadName());
            }
        }
    }

    @Override
    public void run() {
        if(flag == 0) {
            transfer(a, b, 200);
        }
        if(flag == 1) {
            transfer(b, a, 200);
        }
    }

    public static void transfer(Account a, Account b, int money) {
//        synchronized (a) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if(a.balance < money) {
//                System.out.println("账户余额不足");
//                return;
//            }
//            synchronized (b) {
//                System.out.println("成功转账" + money + "元");
//                a.balance -= money;
//                b.balance += money;
//            }
//        }
        SolveMustLock.transfer(a, b, money);
    }

    static class Account {
        int balance;
        public Account(int balance) {
            this.balance = balance;
        }
    }
}
