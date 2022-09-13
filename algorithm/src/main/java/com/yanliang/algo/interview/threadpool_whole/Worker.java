package com.yanliang.algo.interview.threadpool_whole;

import java.util.LinkedList;

/**
 * 工作线程
 *
 * @author yanliang
 */
public class Worker implements Runnable{

    private boolean running;

    private LinkedList<Runnable> jobs;

    public Worker(LinkedList<Runnable> jobs) {
        this.jobs = jobs;
        this.running = true;
    }

    @Override
    public void run() {
        while (true) {
            Runnable job = null;
            synchronized (jobs) {
                if (jobs.isEmpty()) {
                    try {
                        jobs.wait(); // 等待唤醒
                    } catch (InterruptedException e) {
                        //感知到外部对该线程的中断操作，返回
                        Thread.currentThread().interrupt();
                        return;
                    }
                } else {
                    job = jobs.removeLast();
                }
            }
            //执行job
            if (job != null) {
                job.run();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
