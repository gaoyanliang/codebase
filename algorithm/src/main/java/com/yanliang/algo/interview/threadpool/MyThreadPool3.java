package com.yanliang.algo.interview.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yanliang
 */
public class MyThreadPool3 implements ThreadPool<Runnable> {

    // 线程池维护工作者线程的最大数量
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程池维护工作者线程的默认值
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /** 维护一个工作列表,里面加入客户端发起的工作 (无界任务队列) */
    private final LinkedList<Runnable> jobs = new LinkedList<>();

    // 工作者线程的列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者线程的数量
    private int workerNum;
    // 每个工作者线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public MyThreadPool3(int capacity) {
        if (capacity > MAX_WORKER_NUMBERS) {
            this.workerNum = DEFAULT_WORKER_NUMBERS;
        } else {
            this.workerNum = capacity;
        }
        initializeWorkers(this.workerNum);
    }

    /**
     * 初始化每个工作者线程
     *
     * @param num 工作线程数量
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            //添加到工作者线程的列表
            workers.add(worker);
            //启动工作者线程
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (task == null) throw new NullPointerException();
        System.out.println(Thread.currentThread().getName() + " execute task.");
        synchronized (jobs) {
            RunnableFuture<Void> ftask = newTaskFor(task, null);
            jobs.add(ftask);
            jobs.notifyAll();
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
        System.out.println(Thread.currentThread().getName() + " submit task. And wait res...");
        synchronized (jobs) {
            RunnableFuture<T> ftask = newTaskFor(task);
            jobs.add(ftask);
            jobs.notifyAll();
            return ftask;
        }
    }

    /**
     * 关闭线程池即关闭每个工作者线程
     */
    @Override
    public void shutdown() {
        for (Worker w: workers) {
            w.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        //加锁，防止该线程还么增加完成而下个线程继续增加导致工作者线程超过最大值
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if(num>=this.workerNum){
                throw new IllegalArgumentException("超过了已有的线程数量");
            }
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (worker != null) {
                    //关闭该线程并从列表中移除
                    worker.shutdown();
                    workers.remove(i);
                }
            }
            this.workerNum -= num;
        }
    }

    @Override
    public int getJobSize() {
        return workers.size();
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<T>(runnable, value);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable);
    }

    /**
     * 定义工作者线程
     */
    class Worker implements Runnable {
        // 表示是否运行该worker
        private volatile boolean running = true;

        public void run() {
            while (running) {
                Runnable job = null;
                //线程的等待/通知机制
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            jobs.wait();//线程等待唤醒
                        } catch (InterruptedException e) {
                            //感知到外部对该线程的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    } else {
                        // 取出一个job
                        job = jobs.removeFirst();
                    }
                }
                //执行job
                if (job != null) {
                    job.run();
                }
            }
        }

        // 终止该线程
        public void shutdown() {
            running = false;
        }
    }

}

