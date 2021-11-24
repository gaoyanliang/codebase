package com.yanliang.codebase.string;

/**
 * @author yanliang
 */
public class RemoveDuplicateLetters {

	public static void main(String[] args) {
		System.out.println(0x80000000);
		System.out.println(removeDuplicateLetters("bcabc"));
		System.out.println(removeDuplicateLetters("cbacdcbc"));
	}

	public static String removeDuplicateLetters(String s) {
		int[] times = new int[26];
		boolean[] vis = new boolean[26];

		int len = s.length(), i = 0;
		for (int j = 0; j < len; j ++) {
			times[s.charAt(j) - 'a'] ++;
		}

		StringBuilder sb = new StringBuilder();
		while(i < len) {
			char ch = s.charAt(i);
			if (!vis[ch - 'a']) {
				while (sb.length() != 0 && ch <= sb.charAt(sb.length() - 1)) {
					if (times[sb.charAt(sb.length() - 1) - 'a'] > 1) {
						vis[sb.charAt(sb.length() - 1) - 'a'] = false;
						sb.deleteCharAt(sb.length() - 1);
					}
				}
			}

			sb.append(ch);
			vis[ch - 'a'] = true;
			i ++;
		}
		return sb.toString();
	}
}
