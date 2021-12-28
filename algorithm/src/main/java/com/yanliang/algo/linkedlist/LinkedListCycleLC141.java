package com.yanliang.algo.linkedlist;

/**
 * 141. 环形链表 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * <p>如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0
 * 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * <p>如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * <p>[3,2,0,-4] 1 [1,2] 0 [1,2] -1
 */
public class LinkedListCycleLC141 {

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode first = head.next, slow = head;
        while (first != null && first.next != null) {
            first = first.next.next;
            slow = slow.next;
            if (first == slow) return true;
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
