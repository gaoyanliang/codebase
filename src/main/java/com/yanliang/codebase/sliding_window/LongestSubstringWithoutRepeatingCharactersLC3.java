package com.yanliang.codebase.sliding_window;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * abcabcbb 3
 * bbbbb    1
 * pwwkew   3
 * ""       0
 */
public class LongestSubstringWithoutRepeatingCharactersLC3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----- start -----");
		while(true) {
			String s = scanner.next();
			if (s.equals("exit")) break;
			System.out.println(lengthOfLongestSubstring(s));
		}
		System.out.println("----- start -----");
	}

	public static int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> windows = new HashMap<>();

		int left = 0, right = 0, res = 0;
		while (right < s.length()) {
			char ch = s.charAt(right ++);
			windows.put(ch, windows.getOrDefault(ch, 0) + 1);

			while (windows.get(ch) > 1) {
				char out = s.charAt(left ++);
				windows.put(out, windows.get(out) - 1);
			}
			res = Math.max(res, right - left);
		}
		return res;
	}
}
