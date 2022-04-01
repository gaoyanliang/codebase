package com.yanliang.algo.interview.thread;

/**
 * 使用两个线程,一个线程打印1,3,5..另一个打印2,4,6。要求顺序打印从1~100
 *
 * 启动两个
 *
 * @author yanliang
 */
public class TwoThreadPrint extends Thread{

    private static final String ODD = "odd";
    private static final String EVEN = "even";

    /** 共享变量 */
    private static int num = 1;

    /** 共享锁 */
    private static Object lock = new Object();

    /**
     * MESA条件变量
     */
    private String type;

    public TwoThreadPrint(String type) {
        this.type = type;
    }

    @Override
    public void run() {
        try {
            synchronized(lock) {
                while(num < 100) {
                    if (!isMe()) {
                        lock.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + num ++);
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isMe() {
        return (num % 2 == 1 && ODD.equals(type) || (num % 2 == 0 && EVEN.equals(type)));
    }


    public static void main(String[] args) {
        TwoThreadPrint print1 = new TwoThreadPrint(ODD);
        TwoThreadPrint print2 = new TwoThreadPrint(EVEN);

        print1.start();
        print2.start();
    }
}
