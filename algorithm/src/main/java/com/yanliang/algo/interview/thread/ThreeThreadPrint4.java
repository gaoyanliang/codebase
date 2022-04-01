package com.yanliang.algo.interview.thread;

/**
 * 使用三个线程,每个线程打印10个数，交替进行。要求顺序打印从1~100
 * 例如：
 * thread-1： 1 - 10
 * thread-2： 11 - 20
 * thread-3： 21 - 30
 * 。。。
 *
 * 实现：每个线程一次打印 10 个数
 * @author yanliang
 */
public class ThreeThreadPrint4 extends Thread{

    /** 共享变量 */
    private volatile static int num = 1;

    /** 共享锁 */
    private static final Object lock = new Object();

    private volatile static boolean[] types = new boolean[3];

    private int id;

    public ThreeThreadPrint4(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (num <= 100) {

                    while (!isMe()) {
                        lock.wait();

                        // 其他线程已经结束，本线程直接结束
                        if (num > 100) {
                           return ;
                        }
                    }

                    for (int i = 0; i < 10; i ++) {
                        System.out.println(Thread.currentThread().getName() + " : " + num);
                        num ++;
                    }

                    types[id] = false;
                    types[(id + 1) % 3] = true;

                    // 唤醒其他线程
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isMe() {
        return types[id];
    }

    public static void main(String[] args) {
        types[0] = true;
        new ThreeThreadPrint4(0).start();
        new ThreeThreadPrint4(1).start();
        new ThreeThreadPrint4(2).start();
    }
}
