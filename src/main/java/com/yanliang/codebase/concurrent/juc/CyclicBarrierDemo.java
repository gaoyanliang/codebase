package com.yanliang.codebase.concurrent.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yanliang
 * @date 11/20/2020 3:30 PM
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {

		// 集齐7颗龙珠，召唤神龙
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
			System.out.println(" ====> 召唤神龙");
		});

		for (int i = 1; i <= 7; i++) {
			final int tempInt = i;
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t 收集到第 " + tempInt + "颗龙珠");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
