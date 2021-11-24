package com.yanliang.codebase.string;

import java.util.concurrent.locks.Lock;

/**
 * 输入两个字符串S和T，判断T在S中出现的次数(S中能找到几个不同的子串T),这里子串是指在一个字符串中删除零或若干字符而不改变其它字符位置得到的新字符串。如“ACE”是“ABCDE”的子串而“AEC”不是。
 * 要求提示输入S和T，并打印T在S中出现的次数。
 * S=“rabbbit”，T=“rabbit”返回3，因为T可以由S分别删除三个b中的一个得到
 * @author yanliang
 */
public class FindSubString {

	private static int count = 0;

	public static void main(String[] args) {
		findSubString("aaa", "aa", 0, 0);
//		findSubString("rabbbit", "rabbit", 0, 0);
		System.out.println(count);
	}

	public static void findSubString(String s1, String s2, int i, int j) {
		 if (j == s2.length()) {
		 	count ++;
		 	return ;
		 }
		 if (i == s1.length()) {
		 	return ;
		 }
		 for (int k = i; k < s1.length(); k ++) {
		 	if (s1.charAt(k) != s2.charAt(j)) {
		 		continue;
		    } else {
		 		findSubString(s1, s2, k + 1, j + 1);
		    }
		 }
	}

}
