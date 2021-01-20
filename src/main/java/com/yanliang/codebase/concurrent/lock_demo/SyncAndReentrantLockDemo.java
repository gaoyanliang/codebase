package com.yanliang.codebase.concurrent.lock_demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanliang
 * @date 2020/11/2216:00
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            shareResource.print5();
        }, "AA").start();

        new Thread(() -> {
            shareResource.print10();
        }, "BB").start();

        new Thread(() -> {
            shareResource.print15();
        }, "CC").start();
    }
}

class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        int n = 5;
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }

            while (n -- > 0) {
                print(5);
            }
            // 更新标识位
            number = 2;
            // 精确唤醒下一个线程
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print10() {
        int n = 10;
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }

            while (n -- > 0) {
                print(10);
            }
            // 更新标识位
            number = 3;
            // 精确唤醒下一个线程
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print15() {
        int n = 15;
        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }

            while (n -- > 0) {
                print(15);
            }
            System.out.println("====> 最后一个线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print(int n) {
        System.out.println("线程 " + Thread.currentThread().getName() + "\t 打印" + n + "次");
    }
}
