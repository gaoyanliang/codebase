package com.yanliang.codebase.offer.linkedlist;

public class Flatten {

    Node res;
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    public Node dfs(Node head) {
        Node last = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            // 如果有子节点，先处理子节点（处理之前先记录当前节点的下一个节点）
            if (cur.child != null) {
                Node childLast = dfs(cur.child);

                // 将当前节点与当前节点的子节点相连接
                cur.next = cur.child;
                cur.child.prev = cur;

                // 如果 next 不为空，将 last 与next 相连接
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                // 将当前节点与子节点断链
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
