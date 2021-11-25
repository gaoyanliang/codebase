package com.yanliang.codebase.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * @author yanliang
 */
public class PermutationInStringLC567 {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----- start -----");
		while (true) {
			String s1 = scanner.next();
			if (s1.equals("exit")) break;
			String s2 = scanner.next();

			System.out.println(checkInclusion(s1, s2));
		}
		System.out.println("----- end -----");
	}


	/**
	 * 注意下面的 Integer 相等判断要用 equals， 直接用 == 有部分案例跑不过
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean checkInclusion(String s1, String s2) {
		Map<Character, Integer> need = new HashMap<>(s1.length());
		Map<Character, Integer> window = new HashMap<>(s1.length() + 1);

		for (int i = 0; i < s1.length(); i ++) {
			char ch = s1.charAt(i);
			need.put(ch, need.getOrDefault(ch, 0) + 1);
		}

		int left = 0, right = 0, valid = 0;
		int len = s2.length();
		while (right < len) {
			char in = s2.charAt(right ++);
			if (need.containsKey(in)) {
				window.put(in, window.getOrDefault(in, 0) + 1);
				if (window.get(in).equals(need.getOrDefault(in, 0))) valid ++;
			}

			while (right - left >= s1.length()) {
				if (valid == need.size()) return true;
				char out = s2.charAt(left ++);
				if (need.containsKey(out)) {
					if (window.get(out).equals(need.get(out))) valid --;
					window.put(out, window.get(out) - 1);
				}
			}
		}
		return false;
	}

}
