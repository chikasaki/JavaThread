package com.company.threadcoreknowledge.chapter10deadlock;

import java.util.concurrent.atomic.AtomicInteger;

public class DiningPhilosophers {
    private static class Philosopher implements Runnable{

        private Object leftChop;
        private Object rightChop;

        private static AtomicInteger tickets = new AtomicInteger(NUM_PHILOSOPHER - 1);

        public Philosopher(Object leftChop, Object rightChop) {
            this.leftChop = leftChop;
            this.rightChop = rightChop;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    while(tickets.get() == 0);
                    synchronized (leftChop) {
                        tickets.decrementAndGet();
                        doAction("Pick up left chop");
                        synchronized (rightChop) {
                            doAction("Pick up right chop and eat");

                            doAction("Put down right chop");
                            tickets.incrementAndGet();
                        }
                        doAction("Put down left chop");
                    }
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        private static void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long)(Math.random()*10));
        }
    }

    private static int NUM_PHILOSOPHER = 5;

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHER];
        Object[] chops = new Object[NUM_PHILOSOPHER];
        for (int i = 0; i < NUM_PHILOSOPHER; i++) {
            chops[i] = new Object();
        }

        for (int i = 0; i < NUM_PHILOSOPHER; i++) {
            Object leftChop = chops[i];
            Object rightChop = chops[(i + 1) % NUM_PHILOSOPHER];
            philosophers[i] = new Philosopher(leftChop, rightChop);
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
        }
    }
}
