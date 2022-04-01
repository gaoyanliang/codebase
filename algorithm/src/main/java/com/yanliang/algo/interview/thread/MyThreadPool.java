package com.yanliang.algo.interview.thread;

/**
 * @author yanliang
 */

import com.sun.tools.javac.util.Assert;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义简单线程池
 */
public class MyThreadPool{
    /**存放线程的集合*/
    private ArrayList<MyThead> threads;
    /**任务队列*/
    private TaskQueue taskQueue;
    /**线程池初始限定大小*/
    private int threadNum;
    /**已经工作的线程数目*/
    private int workThreadNum;

    private final ReentrantLock mainLock = new ReentrantLock();

    public MyThreadPool(int initPoolNum) {
        threadNum = initPoolNum;
        threads = new ArrayList<>(initPoolNum);
        taskQueue = new TaskQueue();

        threadNum = initPoolNum;
        workThreadNum = 0;
    }

    /**
     * 异步执行任务, 返回 Future 对象
     *
     * @param callable 要执行的任务
     * @param <T>      任务的返回值类型
     * @return 返回一个 Future, 任务执行完成时其状态变更为 Done.
     */
    public <T> Future<T> submit(Callable<T> callable) {
        RunnableFuture ftask = newTaskFor(callable);
        taskQueue.addTask(ftask);
        initThread();
        return ftask;
    }

    protected static RunnableFuture newTaskFor(Callable callable) {
        return new FutureTask(callable);
    }

    // 初始化线程
    private void initThread() {
        if (threads.size() <= threadNum) {
            MyThead thread = new MyThead(null);
            threads.add(thread);
            thread.start();
        } else {
            rejectTask();
        }
    }

    //execute方法是整个线程池的核心逻辑
//    public void execute(Runnable runnable) {
//        try {
//            mainLock.lock();
//            //1. 线程池未满，每加入一个任务则开启一个线程
//            if(workThreadNum < threadNum) {
//                MyThead myThead = new MyThead(runnable);
//                myThead.start();
//                threads.add(myThead);
//                workThreadNum++;
//            }
//            //2. 线程池已满，放入任务队列，等待有空闲线程时执行
//            else {
//                //3. 队列已满，无法添加时，拒绝任务
//                if(!taskQueue.offer(runnable)) {
//                    rejectTask();
//                }
//            }
//        } finally {
//            mainLock.unlock();
//        }
//    }

    private void rejectTask() {
        System.out.println("任务队列已满，无法继续添加，请扩大您的初始化线程池！");
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        MyThreadPool myThreadPool = new MyThreadPool(5);

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = myThreadPool.submit(createCallable());
            int res = future.get(1, TimeUnit.SECONDS);
            Assert.check(res == 1);
        }
    }

    /**
     * 创建一个虚拟的任务
     *
     * //@param wait        指定任务耗时, 毫秒
     * //@param shouldThrow 指定任务是否抛出异常, 如果为 true, 任务将抛出一个 Exception
     * @return 返回一个虚拟任务, 在开始执行后大约 wait 毫秒后执行完毕
     */
    private static Callable<Integer> createCallable() {
        return () -> {
            System.out.println(Thread.currentThread().getName() + "---");
            return 1;
        };
    }

    class MyThead extends Thread{
        private RunnableFuture task;

        public MyThead(RunnableFuture runnable) {
            this.task = runnable;
        }
        @SneakyThrows
        @Override
        public void run() {
            //该线程一直启动着，不断从任务队列取出任务执行
            while (true) {
                //如果初始化任务不为空，则执行初始化任务
                if(task != null) {
                    try {
                        task.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    task = null;
                }
                //否则去任务队列取任务并执行
                else {
                    RunnableFuture queueTask = taskQueue.getTask();
                    if(queueTask != null) {
                        try {
                            queueTask.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    // 任务队列
    class TaskQueue {
        private final LinkedList<RunnableFuture> tasks = new LinkedList<>();

        // 获取任务
        public RunnableFuture getTask() throws InterruptedException{
            while (tasks.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " task queue is empty, wait....");
                tasks.wait();
            }
            return tasks.removeFirst();
        }

        // 添加任务
        public void addTask(RunnableFuture future) {
            synchronized(tasks) {
                tasks.add(future);
                tasks.notifyAll();
            }
        }
    }
}
