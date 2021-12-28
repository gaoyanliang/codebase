package com.yanliang.algo.leetcode.linked_list;

/** @author yanliang */
public class LinkedListReserve {

    private static Node successor = null;

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        Node node = reserve(head);
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();

        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        Node node1 = reserve(head1, 3);
        while (node1 != null) {
            System.out.print(node1.val + ", ");
            node1 = node1.next;
        }
        System.out.println();

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = new Node(6);
        Node node2 = reserve(head2, 2, 4);
        while (node2 != null) {
            System.out.print(node2.val + ", ");
            node2 = node2.next;
        }
    }

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public static Node reserve(Node head) {
        if (head == null || head.next == null) return head;
        Node last = reserve(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 反转以 head 为起点的 n 个节点，返回新的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public static Node reserve(Node head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        Node last = reserve(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    /**
     * 给一个索引区间 [m,n]（索引从 1 开始），仅仅反转区间中的链表元素。
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static Node reserve(Node head, int m, int n) {
        if (m == 1) {
            return reserve(head, n);
        }
        head.next = reserve(head.next, m - 1, n - 1);
        return head;
    }
}
