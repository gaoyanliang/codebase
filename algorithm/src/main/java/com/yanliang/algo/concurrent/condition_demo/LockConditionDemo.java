package com.yanliang.algo.concurrent.condition_demo;

/**
 * @author yanliang
 * @date 11/12/2020 2:41 PM
 */
public class LockConditionDemo {

    public static void main(String[] args) {
        Message msg = new Message();
        Thread producer = new Thread(new MessageProducer(msg));
        Thread consumer = new Thread(new MessageConsumer(msg));
        producer.start();
        consumer.start();
    }
}
