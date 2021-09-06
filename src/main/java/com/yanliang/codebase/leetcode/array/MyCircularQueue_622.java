package com.yanliang.codebase.leetcode.array;

/**
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
			len --;
			return true;
		}

		/**
		 * 从队首获取元素。如果队列为空，返回 -1 。
		 * @return
		 */
		public int Front() {
			if (isEmpty()) return -1;
			return queue[headIndex];
		}

		/**
		 * 获取队尾元素。如果队列为空，返回 -1 。
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
