package com.yanliang.codebase.leetcode.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 * @author yanliang
 * @date 11/18/2020 8:46 AM
 */
public class MinWindow_76 {

	public static void main(String[] args) {

		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(minWindow("A", "A"));
		System.out.println(minWindow("Aa", "Aa"));
		System.out.println(minWindow("aa", "aa"));

	}

	public static String minWindow(String s, String t) {
		Map<Character, Integer> map = new HashMap<>();
		Map<Character, Integer> window = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			addValue(map, t.charAt(i));
		}

		int left = 0, right = 0;
		int valid = 0;
		// 记录最小覆盖子串的起始索引及长度
		int start = 0, len = Integer.MAX_VALUE;

		while (right < s.length()) {
			// c 是将移入窗口的字符 & 右移窗口
			char c = s.charAt(right);
			right ++;
			// 更新窗口内数据
			if (map.containsKey(c)) {
				addValue(window, c);
				if (getValue(map, c) == getValue(window, c)){
					valid ++;
				}
			}

			// 判断左侧窗口是否要收缩
			while (valid == map.size()) {
				if (right - left < len) {
					len = right - left;
					start = left;
				}
				char c1 = s.charAt(left);
				left ++;
				if (map.containsKey(c1)) {
					subValue(window, c1);
					if (getValue(map, c1) > getValue(window, c1)) {
						valid --;
					}
				}
			}
		}
//		System.out.println("start: " + start + " len " + len);
		return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
	}

	public static int getValue(Map<Character, Integer> map, Character key) {
		return map.getOrDefault(key, 0);
	}

	public static void addValue(Map<Character, Integer> map, Character key) {
		map.put(key, map.getOrDefault(key, 0) + 1);
	}

	public static void subValue(Map<Character, Integer> map, Character key) {
		if (getValue(map, key) > 0) {
			map.put(key, map.get(key) - 1);
		}
	}
}
