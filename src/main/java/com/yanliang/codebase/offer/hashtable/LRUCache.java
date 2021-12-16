package com.yanliang.codebase.offer.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 031. 最近最少使用缓存
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 */
public class LRUCache {

    Map<Integer, Node> table;
    DoubleList cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.table = new HashMap<>();
        this.cache = new DoubleList();
    }

    public int get(int key) {
        Node node = table.get(key);
        if (node == null) {
            return -1;
        }
        makeRecently(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = table.get(key);
        if (node != null) {
            table.remove(key);
            cache.delete(node);
        }

        if (cache.getSize() >= this.capacity) {
            removeLast();
        }

        Node newNode = new Node(key, value);
        cache.insert(newNode);
        table.put(key, newNode);
    }

    /**
     * 将节点新插入链表尾部（尾部为新数据）
     *
     * @param node
     */
    public void makeRecently(Node node) {
        cache.delete(node);
        cache.insert(node);
    }

    public void removeLast() {
        Node del = cache.deleteLast();
        table.remove(del.key);
    }


    // -----------------------------------------------

    class DoubleList {
        int size;
        Node head;
        Node tail;
        DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        /**
         * 向链表中插入一个新节点
         *
         * @param node
         */
        public void insert(Node node) {
            tail.pre.next = node;
            node.pre = tail.pre;
            node.next = tail;
            tail.pre = node;
            size ++;
        }

        /**
         * 删除一个节点
         *
         * @param node
         */
        public void delete(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size --;
        }

        /**
         * 删除最老的节点
         *
         * @return
         */
        public Node deleteLast() {
            if (head.next == tail) return null;
            Node del = head.next;
            delete(del);
            return del;
        }

        /**
         * 获取链表中节点数量
         *
         * @return
         */
        public int getSize() {
            return this.size;
        }
    }

    class Node {
        int key;
        int value;
        Node next;
        Node pre;
        Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
