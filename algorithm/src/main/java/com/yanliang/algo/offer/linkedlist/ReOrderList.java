package com.yanliang.algo.offer.linkedlist;

/**
 * 剑指 Offer II 026. 重排链表 https://leetcode-cn.com/problems/LGjMqU/
 *
 * <p>给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * <p>L0 → L1 → … → Ln-1 → Ln 请将其重新排列后变为：
 *
 * <p>L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * <p>不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * <p>示例 1:
 *
 * <p>输入: head = [1,2,3,4] 输出: [1,4,2,3] 示例 2:
 *
 * <p>输入: head = [1,2,3,4,5] 输出: [1,5,2,4,3]
 *
 * <p>提示：
 *
 * <p>链表的长度范围为 [1, 5 * 104] 1 <= node.val <= 1000
 */
public class ReOrderList {

    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode mid = findMid(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;

        l2 = reverse(l2);

        while (l1 != null && l2 != null) {
            ListNode tmp1 = l1.next;
            ListNode tmp2 = l2.next;

            l1.next = l2;
            l1 = tmp1;

            l2.next = l1;
            l2 = tmp2;
        }
    }

    public ListNode findMid(ListNode fast) {
        ListNode slow = fast;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, tmp = head;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
