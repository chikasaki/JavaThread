package com.company.threadcoreknowledge.chapter10deadlock;

/**
 * 使用顺序获取锁的策略，来避免死锁
 * 值得注意的是，这里采用变量哈希值来作为获取锁顺序的依据；
 * 如果发生哈希冲突，会使用额外的lock作为"加时策略"来避免死锁
 */
public class SolveMustLock {
    private static Object lock = new Object();

    public static void transfer(TransferMoney.Account a, TransferMoney.Account b, int money) {
        int ahash = System.identityHashCode(a);
        int bhash = System.identityHashCode(b);
        if(ahash < bhash) {
            synchronized (a) {
                synchronized (b) {
                    helptransfer(a, b, money);
                }
            }
        } else if (ahash > bhash) {
            synchronized (b) {
                synchronized (a) {
                    helptransfer(a, b, money);
                }
            }
        } else {
            synchronized (lock) {
                synchronized (a) {
                    synchronized (b) {
                        helptransfer(a, b, money);
                    }
                }
            }
        }
    }

    private static void helptransfer(TransferMoney.Account a, TransferMoney.Account b, int money) {
        if(a.balance < money) {
            System.out.println("余额不足，转账失败");
        }
        a.balance -= money;
        b.balance += money;
        System.out.println("成功转账" + money + "元");
    }
}
