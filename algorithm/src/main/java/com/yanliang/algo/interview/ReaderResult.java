package com.yanliang.algo.interview;

import java.util.concurrent.CountDownLatch;

/** N个线程等待1个线程的计算结果 @author yanliang */
public class ReaderResult extends Thread {
    Calculator c;
    CountDownLatch counter;

    public ReaderResult(Calculator c, CountDownLatch counter) {
        this.counter = counter;
        this.c = c;
    }

    static class Calculator extends Thread {
        int total;

        public void run() {
            synchronized (this) {
                for (int i = 0; i < 101; i++) {
                    total += i;
                }
                // 通知所有在此对象上等待的线程
                this.notifyAll();
            }
        }
    }

    @Override
    public void run() {
        synchronized (c) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting result...");
                counter.countDown();
                c.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " received result: " + c.total);
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CountDownLatch counter = new CountDownLatch(3);

        // 启动三个线程，分别获取计算结果
        ReaderResult t1 = new ReaderResult(calculator, counter);
        ReaderResult t2 = new ReaderResult(calculator, counter);
        ReaderResult t3 = new ReaderResult(calculator, counter);
        t1.start();
        t2.start();
        t3.start();

        // 启动计算线程
        try {
            counter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calculator.start();
    }
}
