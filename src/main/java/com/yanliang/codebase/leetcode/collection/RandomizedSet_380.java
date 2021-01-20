//package com.yanliang.note.leetcode.collection;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Vector;
//
///**
// * 随机集合：常数时间
// * @author yanliang
// * @date 10/10/2020 9:09 AM
// */
//public class RandomizedSet_380 {
//    // 存储元素的值
//	int[] nums = {};
//
//	// 记录每个元素对应在 nums 中的索引 (key: val   value: index)
//	Map<Integer, Integer> valToIndex = new HashMap<>();
//
//	/**
//	 * 如果 val 不存在集合中，则插入并返回 true，否则直接返回 false
//	 * @param val
//	 * @return
//	 */
//	public boolean insert(int val) {
//		// 若 val 已存在，不用再插入
//		if (valToIndex.keySet().contains(val)) {
//			return false;
//		}
//		// 若 val 不存在，插入到 nums 尾部，
//		// 并记录 val 对应的索引值
//		valToIndex.put(val, nums.length);
//		nums[nums.length] = val;
//		return true;
//	}
//
//	/**
//	 * 如果 val 在集合中，则删除并返回 true，否则直接返回 false
//	 * @param
//	val
//	 * @return
//	 */
//	public boolean remove(int val) {
//		// 若 val 不存在，不用再删除
//		if (!valToIndex.keySet().contains(val)) {
//			return false;
//		}
//		Integer index = valToIndex.get(val);
//	}
//
//	/**
//	 * 从集合中等概率地随机获得一个元素
//	 * @return
//	 */
//	public int getRandom() {}
//}
