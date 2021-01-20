package com.yanliang.codebase.concurrent.lock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanliang
 * @date 11/18/2020 2:41 PM
 */
public class AQSDemo {

	public static void main(String[] args) {

		ReentrantLock lock = new ReentrantLock();

		// 案例：银行办理业务。（模拟AQS如何进行线程的管理和通知唤醒机制）

		// 三个线程模拟三个银行网点，受理窗口办理业务的顾客

		// A 顾客是第一个顾客，此时受理窗口没有任何人，A可以直接去办理
		new Thread(() -> {
			lock.lock();
			try {
				System.out.println(" -------> A thread come in");
				// 暂停几秒钟线程
				try {
					TimeUnit.SECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}, "A").start();


		// B 顾客是第二个顾客, 由于受理窗口只有一个（只能有一个线程持有锁），此时B只能等待
		// 进入候客区
		new Thread(() -> {
			lock.lock();
			try {
				System.out.println(" -------> B thread come in");
				// 暂停几秒钟线程
				try {
					TimeUnit.SECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}, "B").start();

		// C 顾客是第三个顾客, 由于受理窗口只有一个（只能有一个线程持有锁），此时C只能等待
		// 进入候客区
		new Thread(() -> {
			lock.lock();
			try {
				System.out.println(" -------> C thread come in");
				// 暂停几秒钟线程
				try {
					TimeUnit.SECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}, "C").start();

	}

}
