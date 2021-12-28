package com.yanliang.algo.concurrent.block_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按照FIFO（先进先出）原则对元素进行排序 LinkedBlockingQueue:
 * 一个基于链表结构的有界阻塞队列，此队列按照FIFO（先进先出）原则对元素进行排序，吞吐量通常要高于ArrayBlockingQueue SynchronousQueue:
 * 一个存储元素的阻塞队列。每个插入操作必须等另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量要高于
 *
 * <p>1. 队列 2. 阻塞队列 2.1 阻塞队列有没有好的一面 2.2 不得不阻塞，如何管理
 *
 * @author yanliang
 * @date 11/20/2020 3:50 PM
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.add("a");
        blockingQueue.add("a");
        blockingQueue.add("a");
    }
}
