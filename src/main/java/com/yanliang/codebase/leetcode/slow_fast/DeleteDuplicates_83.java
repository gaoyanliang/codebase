package com.yanliang.codebase.leetcode.slow_fast;

/**
 * @author yanliang
 * @date 2020/10/821:31
 */
public class DeleteDuplicates_83 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(3);


//        while (head.next != null) {
//            System.out.print(head.val + " ");
//            head = head.next;
//        }
//        System.out.println();


        ListNode res = deleteDuplicates(head);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
        System.out.println();
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                // nums[slow] = nums[fast];
                slow.next = fast;
                // slow++;
                slow = slow.next;
            }
            // fast++
            fast = fast.next;
        }
        // 断开与后面重复元素的连接
        slow.next = null;
        // 注意🐖：这里返回的head
        return head;
    }

    /**
     * 链表节点
     */
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
