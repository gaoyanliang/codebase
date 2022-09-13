package com.yanliang.algo.interview.threadpool_whole;

import com.sun.tools.javac.util.Assert;
import com.yanliang.algo.interview.threadpool_demo.MyThreadPool3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yanliang
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPool threadPool = new CustomThreadPool(10);

        for (int i = 0; i < 10; i ++) {
            threadPool.execute(createRunnable());

            Future<Integer> submit = threadPool.submit(createCallable());
            Assert.check(submit.get(1, TimeUnit.SECONDS) == 1);
        }

        threadPool.shutdown();

        System.out.println("++++++++" + threadPool.getJobSize());

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
            System.out.println(Thread.currentThread().getName() + " run callable task ");
            return 1;
        };
    }

    private static Runnable createRunnable() {
        return () -> System.out.println(Thread.currentThread().getName() + " run runnable task ");
    }
}
