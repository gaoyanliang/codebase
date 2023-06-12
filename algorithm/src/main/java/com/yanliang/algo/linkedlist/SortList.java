package com.yanliang.algo.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author yanliang */
public class SortList {

    static ListNode tailHead = null;

    // 4  2  1  3
    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

//        ListNode ret = reverseBetween(node, 1, 2);
//        ListNode tmp = reverseBetween(ret, 3, 4);

        ListNode ret = reverseKGroup(node, 2);

        System.out.println(ret);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        int len = getLen(head);
        if (k > len) return head;

        int l = 1, r = k;
         ListNode ret = head;
         while (r < len) {
             ret = reverseBetween(ret, l, r);
             l = r + 1;
             r = r + k;
             tailHead = null;
         }
        return ret;
    }

    public static int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len ++;
            head = head.next;
        }
        return len;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reversePreN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public static ListNode reversePreN(ListNode head, int n) {
        if (n == 1) {
            tailHead = head.next;
            return head;
        }

        ListNode last = reversePreN(head.next, n - 1);
        head.next.next = head;
        head.next = tailHead;
        return last;
    }







    public static ListNode sortList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null) {
            list.add(tmp.val);
            tmp = tmp.next;
        }
        Collections.sort(list);
        ListNode res = new ListNode();
        ListNode next = res;
        for (int n : list) {
            next.next = new ListNode(n);
            next = next.next;
        }
        return res.next;
    }

    static class ListNode {
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
