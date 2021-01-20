package com.yanliang.codebase.leetcode.linked_list;

/**
 * 链表去重
 * @author yanliang
 * @date 9/4/20204:12 PM
 */
public class DeleteDuplicates {

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(3);
		head.next.next.next.next.next = new Node(3);
		head.next.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next.next = new Node(4);

		Node node = deleteDuplicates(head);
		while (node != null) {
			System.out.print(node.val + ", ");
			node = node.next;
		}

		// output: 1, 2, 3, 4,
	}

	public static Node deleteDuplicates(Node head){
		if (head == null) {
			return null;
		}
		Node slow = head, fast = head.next;
		while (fast != null) {
			if (slow.val != fast.val) {
				slow.next = fast;
				slow = slow.next;
			}
			fast = fast.next;
		}
		slow.next = null;
		return head;
	}
}
