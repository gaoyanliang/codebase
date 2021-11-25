package com.yanliang.codebase.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最小覆盖子串
 *
 * 题目描述：给定两个字符串 S 和 T，请在 S 中找出包含 T 中所有字符的最小子串。
 *
 * 如果 S 中不存在，返回 “”
 * 如果 S 中存在，保证唯一
 *
 * @author yanliang
 */
public class MinimumWindowSubstringLC76{

	public static void main(String[] args) {
		System.out.println("--------- start ---------");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String s = scanner.next();
			if (s.equals("exit")) break;
			String t = scanner.next();
			System.out.println(minimumWindowSubstring(s, t));
		}
		System.out.println("--------- end ---------");
	}

	/**
	 * 注意下面的 Integer 相等判断要用 equals， 直接用 == 有部分案例跑不过
	 * @param s
	 * @param t
	 * @return
	 */
	public static String minimumWindowSubstring(String s, String t) {
		Map<Character, Integer> need = new HashMap<>();
		Map<Character, Integer> windows = new HashMap<>();

		for (int i = 0; i < t.length(); i ++) {
			char ch = t.charAt(i);
			need.put(ch, need.getOrDefault(ch, 0) + 1);
		}

		int left = 0, right = 0, vaild = 0;
		int len = Integer.MAX_VALUE, start = 0;
		while (right < s.length()) {
			char in = s.charAt(right ++);
			if (need.containsKey(in)) {
				windows.put(in, windows.getOrDefault(in, 0) + 1);
				if (windows.get(in).equals(need.get(in))) vaild ++;
			}

			while (vaild == need.size()) {
				if (right - left < len) {
					len = right - left;
					start = left;
				}
				char out = s.charAt(left ++);
				if (need.containsKey(out)) {
					windows.put(out, windows.get(out) - 1);
					if (windows.get(out) < need.get(out)) vaild --;
				}
			}
		}
		return len == Integer.MAX_VALUE ? "" : s.substring(start, len + start);
	}

}