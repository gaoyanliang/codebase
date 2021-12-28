package com.yanliang.algo.offer.linkedlist;

/**
 * 剑指 Offer II 027. 回文链表 https://leetcode-cn.com/problems/aMhZSa/
 *
 * <p>https://labuladong.gitee.io/algo/2/17/19/
 *
 * <p>给定一个链表的 头节点 head ，请判断其是否为回文链表。
 *
 * <p>如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 *
 * <p>示例 1：
 *
 * <p>输入: head = [1,2,3,3,2,1] 输出: true 示例 2：
 *
 * <p>输入: head = [1,2] 输出: false
 *
 * <p>提示：
 *
 * <p>链表 L 的长度范围为 [1, 105] 0 <= node.val <= 9
 */
public class IsPalindrome {

    private static ListNode left;

    public static boolean isPalindrome(ListNode head) {
        left = head;
        return palindrome(head);
    }

    public static boolean palindrome(ListNode right) {
        if (right == null) return true;
        boolean res = palindrome(right.next);
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

    public static class ListNode {
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
