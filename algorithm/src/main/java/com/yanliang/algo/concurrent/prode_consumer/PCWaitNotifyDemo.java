package com.yanliang.algo.concurrent.prode_consumer;

/**
 * @author yanliang
 * @date 2/19/2021 4:55 PM
 */
public class PCWaitNotifyDemo {

    private static int count = 0;
    private static final int buffCount = 10;
    private static String LOCK = "lock";

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (LOCK) {
                    while (count == buffCount) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "-生产者生产，数量为:" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "-消费者消费，数量为：" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        PCWaitNotifyDemo demo = new PCWaitNotifyDemo();
        new Thread(demo.new Producer()).start();
        new Thread(demo.new Consumer()).start();
        new Thread(demo.new Producer()).start();
        new Thread(demo.new Consumer()).start();
        new Thread(demo.new Producer()).start();
        new Thread(demo.new Consumer()).start();
    }
}
