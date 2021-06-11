package com.yanliang.codebase.leetcode.lru;

/**
 * @author yanliang
 */
public class DoubleList {
	private int size;
	private Node head, tail;

	public DoubleList() {
		this.size = 0;
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	public void addLast(Node node) {
		node.next = tail;
		node.prev = tail.prev;
		tail.prev.next = node;
		tail.prev = node;
		size++;
	}

	public void remove(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
		size --;
	}

	public Node removeFirst() {
		if (head.next == tail) return null;
		Node first = head.next;
		remove(first);
		return first;
	}

	public int getSize() {
		return size;
	}
}
