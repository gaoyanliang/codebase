package com.yanliang.codebase.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanliang
 * @date 11/8/2020 11:13 AM
 */
public class MinDeletions_5562 {


	public static void main(String[] args) {
//		System.out.println(minDeletions("aab"));
//		System.out.println(minDeletions("aaabbbcc"));
//		System.out.println(minDeletions("ceabaacb"));
		System.out.println(minDeletions("bbbbnmk"));
	}

	public static int minDeletions(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int len =  s.length();
		int[] chr = new int[26];
		for (int i = 0; i < len; i++) {
			chr[s.charAt(i) - 'a'] ++;
		}
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (chr[i] != 0) {
				ans.add(chr[i]);
			}
		}
		int deletions = 0;
		for (int i = 0; i < ans.size(); i++) {
			Integer remove = ans.remove(i);
			while (ans.contains(remove) && remove > 0) {
				deletions ++;
				remove --;
			}
			ans.add(i, remove);
		}
		return deletions;
	}

}
