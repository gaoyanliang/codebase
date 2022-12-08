package com.yanliang.algo.linkedlist;

import java.util.List;

/**
 * 面试题 02.02. 返回倒数第 k 个节点  https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * @author yanliang
 */
public class KthToList {

    public ListNode removeNthFromEnd(ListNode head, int k) {
        if (head == null) return null;

        int len = 0;
        ListNode listNode = head;
        while (listNode != null) {
            len ++;
            listNode = listNode.next;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;

        for (int i = 1; i <= len - k; i++) {
            p = p.next;

            if (p == null) return dummy.next;
        }
        p.next = p.next.next;

        return dummy.next;
    }

    public int kthToList(ListNode head, int k) {
        ListNode fast = head, slow = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
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
