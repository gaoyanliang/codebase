package com.yanliang.algo.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程轮流打印1-100
 * 例如：
 * thread-1：1
 * thread-2：2
 * thread-3：3
 * thread-1：4
 * 。。。
 *
 * @author yanliang
 */
public class ThreeThreadPrint3 {


    public static void main(String[] args) throws InterruptedException {
        Thread work0 = new Thread(new PrintWork(0));
        Thread work1 = new Thread(new PrintWork(1));
        Thread work2 = new Thread(new PrintWork(2));

        work0.start();
        work1.start();
        work2.start();

    }

    /**
     * 打印任务
     */
    static class PrintWork implements Runnable {

        /**
         * 锁对象（static final）
         */
        public static final ReentrantLock lock = new ReentrantLock();
        public static final Condition condition = lock.newCondition();

        /**
         * 打印的值（static）
         */
        public static int num = 1;

        private int workId;

        public PrintWork(int workId) {
            this.workId = workId;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 显式枷锁
                    lock.lock();

                    // 如果值大于 100， 打印任务结束
                    if (num > 100) {
                        break ;
                    }

                    // 判断当前值是否应该由当前线程打印
                    if (num % 3 == workId) {
                        System.out.println("workId: " + workId + " print " + num ++);
                    } else {
                        // 如果当前值不该由当前获取锁的线程打印 wait
                        try {
                            condition.await();
                        } catch (Exception egrion){}
                    }

                    // 唤醒其他线程
                    condition.signalAll();
                } catch (Exception e) {
                    // throw exception
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
