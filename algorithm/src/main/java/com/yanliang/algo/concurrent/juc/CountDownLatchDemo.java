package com.yanliang.algo.concurrent.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author yanliang
 * @date 11/20/2020 3:10 PM
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        // 模拟6个学生 + 1个班长上晚自习的场景
        // 当学生走完之后，班长才能锁门
        for (int i = 0; i < 6; i++) {
            new Thread(
                            () -> {
                                System.out.println(
                                        Thread.currentThread().getName() + "\t 上完晚自习，离开教室.");
                                countDownLatch.countDown();
                            },
                            String.valueOf(i))
                    .start();
        }
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t 所有同学均已离开教室，班长锁门走人");
    }
}
