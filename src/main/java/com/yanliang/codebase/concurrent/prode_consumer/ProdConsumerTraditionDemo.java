package com.yanliang.codebase.concurrent.prode_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个初始化为0的变量，两个线程交替操作，一个加，一个减，来5轮
 * 1. 线程  ---> 操作  --->  资源类
 * 2. 判断  ---> 干活  --->  通知
 * 3. 通过 while 防止在多线程的情况下 await 方法虚假唤醒
 * @author yanliang
 * @date 2020/11/2215:16
 */
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
            }
        }, "BB").start();

        // 超过两个线程之后，await（） 需要在while中执行，才能保证不会出现虚假唤醒的情况

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
            }
        }, "CC").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
            }
        }, "DD").start();

    }
}


class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number ++;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "\t" + number);
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number --;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "\t" + number);
        } finally {
            lock.unlock();
        }
    }
}