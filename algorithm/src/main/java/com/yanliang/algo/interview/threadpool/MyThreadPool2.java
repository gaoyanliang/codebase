package com.yanliang.algo.interview.threadpool;

/**
 * 自定义线程池，实现 submit 提交
 * @author yanliang
 */

import com.sun.tools.javac.util.Assert;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义简单线程池
 */
public class MyThreadPool2 {
    /**存放线程的集合*/
    private List<Worker> workers;
    /**任务队列*/
    private LinkedList<Runnable> jobs;
    /**线程池初始限定大小*/
    private int threadNum;
    private int jobNum;

    private final ReentrantLock mainLock = new ReentrantLock();
    private final Condition condition = mainLock.newCondition();

    public MyThreadPool2(int threadNum) {
        this.threadNum = threadNum;
        this.workers = Collections.synchronizedList(new ArrayList<>());
        this.jobs = new LinkedList<>();
        this.jobNum = Integer.MAX_VALUE;
        initThread();
    }

    public MyThreadPool2(int threadNum, int jobNum) {
        this.threadNum = threadNum;
        this.workers = Collections.synchronizedList(new ArrayList<>());
        this.jobs = new LinkedList<>();
        this.jobNum = jobNum;
        initThread();
    }

    // 初始化线程
    private void initThread(){
        for (int i = 0; i < threadNum; i ++) {
            Worker worker = new Worker();
            workers.add(worker);

            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    /**
     * 异步执行任务, 返回 Future 对象
     *
     * @param task 要执行的任务
     * @param <T>      任务的返回值类型
     * @return 返回一个 Future, 任务执行完成时其状态变更为 Done.
     */
    public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
        try {
            mainLock.lock();
            if (jobs.size() >= jobNum) {
                rejectTask();
                return null;
            } else {
                RunnableFuture ftask = newTaskFor(task);
                jobs.add(ftask);
                condition.signalAll();
                return ftask;
            }
        } catch (Exception e) {
            System.out.println("== error ==");
        } finally {
            mainLock.unlock();
        }
        return null;
    }

    protected static RunnableFuture newTaskFor(Callable callable) {
        return new FutureTask(callable);
    }


    private void rejectTask() {
        System.out.println("任务队列已满，无法继续添加，请扩大您的初始化线程池！");
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
            System.out.println(Thread.currentThread().getName() + "--- callable running");
            return 1;
        };
    }

    class Worker extends Thread{
        private volatile boolean running = true;

        @SneakyThrows
        @Override
        public void run() {
            //该线程一直启动着，不断从任务队列取出任务执行
            while (running) {
                //如果初始化任务不为空，则执行初始化任务
                Runnable job = null;
                try {
                    mainLock.lock();
                    if (jobs.isEmpty()) {
                        condition.await();
                    } else {
                        job = jobs.removeFirst();
                    }
                } catch (InterruptedException e) {
                    //感知到外部对该线程的中断操作，返回
                    Thread.currentThread().interrupt();
                    return ;
                } finally {
                    mainLock.unlock();
                }

                if(job != null) {
                    job.run();
                }
            }
        }

        // 终止该线程
        public void shutdown() {
            this.running = false;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        MyThreadPool2 myThreadPool2 = new MyThreadPool2(5);

        for (int i = 0; i < 20; i++) {
            Future<Integer> future = myThreadPool2.submit(createCallable());
            int res = future.get(1, TimeUnit.SECONDS);
            Assert.check(res == 1);
        }
    }

}
