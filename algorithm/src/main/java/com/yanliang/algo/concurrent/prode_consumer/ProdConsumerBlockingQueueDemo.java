package com.yanliang.algo.concurrent.prode_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanliang
 * @date 2020/11/2216:14
 */
public class ProdConsumerBlockingQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(
                        () -> {
                            System.out.println("生产线程" + Thread.currentThread().getName() + "启动");
                            try {
                                myResource.myProd();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        "prod")
                .start();

        new Thread(
                        () -> {
                            System.out.println("消费线程" + Thread.currentThread().getName() + "启动");
                            try {
                                myResource.myConsumer();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        "consumer")
                .start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("停止生产&消费");
        myResource.stop();
    }
}

class MyResource {
    // 默认开启，进行生产+消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.addAndGet(1) + "";
            retValue = blockingQueue.offer(data, 3, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "插入 " + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "插入 " + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("=======> 生产线程暂停");
    }

    public void myConsumer() throws Exception {
        String res = null;
        while (FLAG) {
            res = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (res == null || res.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println("=======> 消费线程结束");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费到 " + res);
        }
    }

    public void stop() {
        FLAG = false;
    }
}
