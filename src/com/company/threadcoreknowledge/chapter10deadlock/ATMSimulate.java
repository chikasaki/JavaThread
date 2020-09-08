package com.company.threadcoreknowledge.chapter10deadlock;

import java.util.Random;

public class ATMSimulate{

    private static final int NUM_ACCOUNT = 50;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_THREADS = 20;
    private static final int TRANSFER_TIMES = 1000000;

    public static void main(String[] args) {
        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNT];
        for(int i = 0; i < NUM_ACCOUNT; i ++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

        class ATMThread extends Thread{
            @Override
            public void run() {
                for(int i = 0; i < TRANSFER_TIMES; i ++) {
                    int fromIdx = random.nextInt(NUM_ACCOUNT);
                    int toIdx = random.nextInt(NUM_ACCOUNT);
                    int money = random.nextInt(NUM_MONEY);
                    TransferMoney.transfer(accounts[fromIdx], accounts[toIdx], money);
                }
            }
        }

        for(int i = 0; i < NUM_THREADS; i ++) {
            new ATMThread().start();
        }
    }
}
