package com.yanliang.algo.leetcode.array;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * <p>循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * <p>你的实现应该支持如下操作：
 *
 * <p>MyCircularQueue(k): 构造器，设置队列长度为 k 。 Front: 从队首获取元素。如果队列为空，返回 -1 。 Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。 deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。 isEmpty():
 * 检查循环队列是否为空。 isFull(): 检查循环队列是否已满。
 *
 * @author yanliang
 */
public class MyCircularQueue_622 {

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1)); // 返回 true
        System.out.println(circularQueue.enQueue(2)); // 返回 true
        System.out.println(circularQueue.enQueue(3)); // 返回 true
        System.out.println(circularQueue.enQueue(4)); // 返回 false，队列已满
        System.out.println(circularQueue.Rear()); // 返回 3
        System.out.println(circularQueue.isFull()); // 返回 true
        System.out.println(circularQueue.deQueue()); // 返回 true
        System.out.println(circularQueue.enQueue(4)); // 返回 true
        System.out.println(circularQueue.Rear()); // 返回 4
    }

    public static class MyCircularQueue {

        private int[] queue;
        private int headIndex;
        private int len;
        private int capacity;

        public MyCircularQueue(int k) {
            queue = new int[k];
            headIndex = 0;
            len = 0;
            capacity = k;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            queue[(headIndex + len) % capacity] = value;
            len += 1;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            headIndex = (headIndex + 1) % capacity;
            len--;
            return true;
        }

        /**
         * 从队首获取元素。如果队列为空，返回 -1 。
         *
         * @return
         */
        public int Front() {
            if (isEmpty()) return -1;
            return queue[headIndex];
        }

        /**
         * 获取队尾元素。如果队列为空，返回 -1 。
         *
         * @return
         */
        public int Rear() {
            if (isEmpty()) return -1;
            return queue[(headIndex + len - 1) % capacity];
        }

        public boolean isEmpty() {
            return len == 0;
        }

        public boolean isFull() {
            return len == capacity;
        }
    }
}
