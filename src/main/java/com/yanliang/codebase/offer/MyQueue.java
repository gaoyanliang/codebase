package com.yanliang.codebase.offer;

import java.util.Stack;

/**
 * 用队列实现栈
 */
class MyQueue {
    Object transLock = new Object();
    Stack<Integer> input;
    Stack<Integer> output;

    /** Initialize your data structure here. */
    public MyQueue() {
        input = new Stack();
        output = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        synchronized(transLock){
            input.push(x);
        }
    }
    private void trans(){
        while(input.size() > 0){
            output.push(input.pop());
        }
    }
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (output.size() == 0){
            synchronized(transLock){
                trans();
            }
        }
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (output.size() == 0){
            synchronized(transLock){
                trans();
            }
        }
        return output.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(input.size()==0 && output.size()==0)
            return true;
        else
            return false;
    }
}

