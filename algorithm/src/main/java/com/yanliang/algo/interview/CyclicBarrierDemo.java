package com.yanliang.algo.interview;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yanliang
 */
public class CyclicBarrierDemo {

    private static final int BARRIER = 5;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(BARRIER, new Runnable() {
            //当所有线程到达barrier时执行
            @Override
            public void run() {
                System.out.println("Inside Barrier");
            }
        });

        for (int i = 0; i < 5; i ++) {
            new WorkThread(cyclicBarrier).start();
        }
    }

    static class WorkThread extends Thread{
        CyclicBarrier barrier;

        public WorkThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting...");
                //线程在这里等待，直到所有线程都到达barrier。
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wolcome " + Thread.currentThread().getName());
        }
    }
}
