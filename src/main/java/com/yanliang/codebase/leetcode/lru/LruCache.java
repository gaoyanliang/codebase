package com.yanliang.codebase.leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanliang
 */
public class LruCache {
	int capcity;
	DoubleList cache;
	Map<Integer, Node> map;

	public LruCache() {
		this(16);
	}

	public LruCache(int capcity) {
		this.capcity = capcity;
		cache = new DoubleList();
		map = new HashMap<>();
	}

	public int get(int key) {
		if (!map.containsKey(key)) return -1;
		makeRecentry(key);
		return map.get(key).value;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			delete(key);
			addRecentry(key, value);
			return ;
		}

		if (cache.getSize() == capcity) {
			deleteLastestRecentry();
		}

		addRecentry(key, value);
	}

	public void makeRecentry(int key) {
		Node node = map.get(key);
		cache.remove(node);
		cache.addLast(node);
	}

	public void addRecentry(int key, int value) {
		Node node = new Node(key, value);
		map.put(key, node);
		cache.addLast(node);
	}

	public void delete(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			map.remove(key);
			cache.remove(node);
		}
	}

	public void deleteLastestRecentry() {
		Node node = cache.removeFirst();
		map.remove(node.key);
	}

}
