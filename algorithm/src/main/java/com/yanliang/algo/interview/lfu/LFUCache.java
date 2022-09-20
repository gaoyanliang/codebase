package com.yanliang.algo.interview.lfu;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU 缓存   https://labuladong.gitee.io/algo/2/23/60/
 * get(key) 方法会去缓存中查询键 key，如果 key 存在，则返回 key 对应的 val，否则返回 -1。
 *
 * put(key, value) 方法插入或修改缓存。如果 key 已存在，则将它对应的值改为 val；如果 key 不存在，则插入键值对 (key, val)。
 *
 * 当缓存达到容量 capacity 时，则应该在插入新的键值对之前，删除使用频次（后文用 freq 表示）最低的键值对。如果 freq 最低的键值对有多个，则删除其中最旧的那个。
 * @author yanliang
 */
public class LFUCache {

    public static void main(String[] args) {
        // 构造一个容量为 2 的 LFU 缓存
        LFUCache cache = new LFUCache(2);

// 插入两对 (key, val)，对应的 freq 为 1
        cache.put(1, 10);
        cache.put(2, 20);

// 查询 key 为 1 对应的 val
// 返回 10，同时键 1 对应的 freq 变为 2
        System.out.println(cache.get(1));

// 容量已满，淘汰 freq 最小的键 2
// 插入键值对 (3, 30)，对应的 freq 为 1
        cache.put(3, 30);

// 键 2 已经被淘汰删除，返回 -1
        System.out.println(cache.get(2));

    }

    Map<Integer, Integer> keyToVal;
    Map<Integer, Integer> keyToFreq;
    Map<Integer, LinkedHashSet<Integer>> freqToKeys;
    int capacity;
    int minFreq;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        freqToKeys = new HashMap<>();
        keyToFreq = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    // 在缓存中查询 key
    public int get(int key) {
        if (!keyToVal.containsKey(key)) return -1;
        increaseFreq(key);
        return keyToVal.get(key);
    }

    // 将 key 和 val 存入缓存
    public void put(int key, int val) {
        if (this.capacity <= 0) return ;

        // 先判断是否已经存在 key
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            increaseFreq(key);
            return;
        }

        // 容量已满，先删除
        if (keyToVal.size() == capacity) {
            removeMinTimeNode();
        }

        // 添加新元素
        keyToVal.put(key, val);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        this.minFreq = 1;
    }


    // ------------------ 内部方法
    // 增加节点的访问次数
    protected void increaseFreq(int key) {
        // 获取当前节点的访问次数
        int freq = keyToFreq.get(key);
        // 当前节点访问次数加 1
        keyToFreq.put(key, freq + 1);

        // 从 time 集合中移除 node，加入到 time + 1 集合中
        freqToKeys.getOrDefault(freq, new LinkedHashSet<>()).remove(key);
        if (freqToKeys.getOrDefault(freq, new LinkedHashSet<>()).size() == 0) {
            freqToKeys.remove(freq);
        }
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);

        // 如果当前节点的访问次数就是最小访问次数，并且该访问次数集合为空，最小访问次数加一
        if (freq == minFreq && !freqToKeys.containsKey(freq)) {
            minFreq++;
        }
    }

    // 移除最少访问次数节点
    protected void removeMinTimeNode() {
        // freq 最小的 key 列表
        LinkedHashSet<Integer> keys = freqToKeys.getOrDefault(minFreq, new LinkedHashSet<>());
        if (keys.isEmpty()) return;

        // 最先被加入的那个 key 就是该被淘汰的
        int key = keys.iterator().next();
        keys.remove(key);
        if (keys.isEmpty()) {
            freqToKeys.remove(minFreq);
        }

        keyToVal.remove(key);
        keyToFreq.remove(key);
    }


}
