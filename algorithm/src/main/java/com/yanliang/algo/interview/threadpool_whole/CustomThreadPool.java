package com.yanliang.algo.interview.threadpool_whole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author yanliang
 */
public class CustomThreadPool implements ThreadPool<Runnable> {

    // 最大线程数量
    public static final int MAX_WORKER_NUMBERS = 10;
    // 默认线程数量
    public static final int DEFAULT_WORKER_NUMBERS = 5;

    // 任务队列
    private final LinkedList<Runnable> jobs = new LinkedList<>();
    // 工作者线程的列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>(MAX_WORKER_NUMBERS));
    private int workerNum;

    public CustomThreadPool(int capacity) {
        if (capacity > MAX_WORKER_NUMBERS) {
            this.workerNum = MAX_WORKER_NUMBERS;
        } else {
            this.workerNum = capacity;
        }
        initWorkerThread(workerNum);
    }

    // 初始化工作线程
    protected void initWorkerThread(int capacity) {
        for (int i = 0; i < capacity; i++) {
            Worker worker = new Worker(jobs);
            //添加到工作者线程的列表
            workers.add(worker);

            //启动工作者线程
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        System.out.println(Thread.currentThread().getName() + " execute task.");
        synchronized (jobs) {
            RunnableFuture<Void> ftask = newTaskFor(task, null);
            jobs.add(ftask);
            jobs.notifyAll();
        }
    }

    @Override
    public Future submit(Callable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        System.out.println(Thread.currentThread().getName() + " execute task.");
        synchronized (jobs) {
            RunnableFuture<Void> ftask = newTaskFor(task);
            jobs.add(ftask);
            jobs.notifyAll();
            return ftask;
        }
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable task, T value) {
        return new FutureTask<>(task, value);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable task) {
        return new FutureTask<>(task);
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
        //加锁，防止该线程还没增加完成而下个线程继续增加导致工作者线程超过最大值
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initWorkerThread(num);
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

}
