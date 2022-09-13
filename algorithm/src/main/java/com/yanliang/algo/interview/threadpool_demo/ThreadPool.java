package com.yanliang.algo.interview.threadpool_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * 自定义线程池接口 {@link MyThreadPool3}
 * @param <Runnable>>
 */
public interface ThreadPool<Runnable>{
    //执行一个任务(Job),这个Job必须实现Runnable
    void execute(Runnable runnable);
    /**
     * 异步执行任务, 返回 Future 对象
     *
     * @param callable 要执行的任务
     * @param <T>      任务的返回值类型
     * @return 返回一个 Future, 任务执行完成时其状态变更为 Done.
     */
    <T> Future<T> submit(Callable<T> callable);
    //关闭线程池
    void shutdown();
    //增加工作者线程，即用来执行任务的线程
    void addWorkers(int num);
    //减少工作者线程
    void removeWorker(int num);
    //获取正在等待执行的任务数量
    int getJobSize();
}
