package com.yanliang.algo.leetcode.linked_list;

/** @author yanliang */
public class CopyRandomList138 {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node next1 = new Node(2);
        Node next2 = new Node(3);
        Node next3 = new Node(4);
        Node next4 = new Node(5);

        head.next = next1;
        head.random = next2;

        next1.next = next2;
        next1.random = next3;

        next2.next = next3;
        next2.random = next4;

        next3.next = next4;
        next3.random = head;

        copyRandomList(head);
        System.out.println();
    }

    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        Node copy = new Node(-1);
        copy.next = head;
        while (head != null) {
            Node node = new Node(head.val);
            node.next = head.next;
            head.next = node;
            head = node.next;
        }

        head = copy.next;
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }

        head = copy.next;
        Node ans = head.next;
        while (head != null) {
            Node tmp = head.next;
            if (head.next != null) head.next = head.next.next;
            head = tmp;
        }
        return ans;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
