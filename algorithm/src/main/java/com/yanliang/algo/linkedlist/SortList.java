package com.yanliang.algo.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** @author yanliang */
public class SortList {

    // 4  2  1  3
    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(1, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(4, node3);

        System.out.println(sortList(node4));
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
