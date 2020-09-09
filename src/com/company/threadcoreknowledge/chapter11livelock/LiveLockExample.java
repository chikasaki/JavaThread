package com.company.threadcoreknowledge.chapter11livelock;

import java.util.Random;

/**
 * 当对方饥饿时，率先把勺子交给对方使用
 */
public class LiveLockExample {
    private static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String name, boolean isHungry) {
            this.name = name;
            this.isHungry = isHungry;
        }

        private void eat(Spoon spoon, Diner spouse) {
//            while(true) {
//                if(!isHungry) {
//                    try {
//                        Thread.sleep((long)(Math.random()*10));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    isHungry = true;
//                }
                while(isHungry) {
                    if(spoon.owner != this) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    Random random = new Random();
                    if(spouse.isHungry && random.nextInt(10) < 9) {
                        System.out.printf("%s: 亲爱的%s你先吃吧\n", name, spouse.name);
                        spoon.owner = spouse;
                        continue;
                    }
                    System.out.printf("%s: 我吃完了\n", name);
                    isHungry = false;
                    spoon.owner = spouse;
                }
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    private static class Spoon {
        private Diner owner;
        public Spoon(Diner owner) {
            this.owner = owner;
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("牛郎", true);
        Diner wife = new Diner("织女", true);

        Spoon spoon = new Spoon(husband);
        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eat(spoon, wife);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eat(spoon, husband);
            }
        }).start();
    }
}
