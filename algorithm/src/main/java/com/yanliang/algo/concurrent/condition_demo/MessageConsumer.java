package com.yanliang.algo.concurrent.condition_demo;

/**
 * @author yanliang
 * @date 11/12/2020 2:40 PM
 */
public class MessageConsumer implements Runnable {

    private Message message;

    public MessageConsumer(Message msg) {
        message = msg;
    }

    @Override
    public void run() {
        while (!message.isEnd()) {
            message.consume();
        }
    }
}
