package com.yanliang.algo.interview.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 LRU 缓存机制   https://labuladong.gitee.io/algo/2/23/59/
 * - [146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)
 * - [剑指 Offer II 031. 最近最少使用缓存](https://leetcode.cn/problems/OrIXps/)
 *
 * 首先要接收一个 capacity 参数作为缓存的最大容量，
 * 然后实现两个 API，一个是 put(key, val) 方法存入键值对，另一个是 get(key) 方法获取 key 对应的 val，
 * 如果 key 不存在则返回 -1。
 *
 *
 *
 * @author yanliang
 */
public class LRUCache {

    public static void main(String[] args) {
        /* 缓存容量为 2 */
        LRUCache cache = new LRUCache(2);
// 你可以把 cache 理解成一个队列
// 假设左边是队头，右边是队尾
// 最近使用的排在队头，久未使用的排在队尾
// 圆括号表示键值对 (key, val)

        cache.put(1, 1);
// cache = [(1, 1)]

        cache.put(2, 2);
// cache = [(2, 2), (1, 1)]

        System.out.println(cache.get(1));       // 返回 1
        System.out.println(cache.get(2));
// cache = [(1, 1), (2, 2)]
// 解释：因为最近访问了键 1，所以提前至队头
// 返回键 1 对应的值 1

        cache.put(3, 3);
// cache = [(3, 3), (1, 1)]
// 解释：缓存容量已满，需要删除内容空出位置
// 优先删除久未使用的数据，也就是队尾的数据
// 然后把新的数据插入队头

        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));
// cache = [(3, 3), (1, 1)]
// 解释：cache 中不存在键为 2 的数据

        cache.put(1, 4);
// cache = [(1, 4), (3, 3)]
// 解释：键 1 已存在，把原始值 1 覆盖为 4
// 不要忘了也要将键值对提前到队头

        System.out.println(cache.get(1));
    }

    Map<Integer/* key */, Node> map;
    DoubleList list;
    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.list = new DoubleList();
        this.capacity = capacity;
    }

    // O(1) 时间复杂度获取值
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        makeRecently(node);
        return node.value;
    }

    // O(1) 时间复杂度添加值
    public void put(int key, int value) {
        // 空间满了之后，先移除最久未访问元素
        if (list.getSize() == capacity) {
            removeLast();
        }

        // 如果已经存在 key 元素，先删除
        if (map.containsKey(key)) {
            removeNode(key);
        }

        addNode(key, value);
    }

    // -------------- 内部方法
    // 将当前节点更新为最新访问的节点，移至链表头部
    protected void makeRecently(Node node) {
        list.remove(node);
        list.addHead(node);
    }

    // 移除最近最少使用的节点
    protected void removeLast() {
        int key = list.removeLast();
        map.remove(key);
    }

    // 添加新节点
    protected void addNode(int key, int value) {
        Node node = new Node(key, value);
        map.put(key, node);
        list.addHead(node);
    }

    // 删除节点
    protected void removeNode(int key) {
        Node node = map.get(key);
        list.remove(node);
        map.remove(key);
    }

}

class DoubleList {
    // 链表头节点和尾节点
    Node head, tail;
    // 链表长度
    int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 头插法
    public void addHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
        size ++;
    }

    // 移除指定节点
    public void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.prev = null;
        node.next = null;
        size --;
    }

    // 移除最久未访问节点（尾节点）,并返回 key
    public int removeLast() {
        Node node = tail.prev;
        remove(node);
        return node.key;
    }

    public int getSize() {
        return size;
    }
}

class Node {
    int key, value;
    Node prev, next;

    public Node (int key, int value) {
        this.key = key;
        this.value = value;
    }
}
