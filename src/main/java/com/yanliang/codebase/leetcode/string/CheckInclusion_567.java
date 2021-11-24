package com.yanliang.codebase.leetcode.string;

import java.util.Arrays;

/**
 * @author yanliang
 */
public class CheckInclusion_567 {

	public static void main(String[] args) {
		System.out.println(checkInclusion("ab", "eidbaooo"));
		System.out.println(checkInclusion("ab", "eidboaoo"));
		System.out.println(checkInclusion("a", "ab"));
		System.out.println(checkInclusion("abc", "bbbca"));
	}

	public static boolean checkInclusion(String s1, String s2) {
		int l1 = s1.length();
		int l2 = s2.length();
		if (l1 > l2) return false;

		int[] ch1 = new int[26];
		int[] ch2 = new int[26];
 		for (int i = 0; i < l1; i ++) {
 			ch1[s1.charAt(i) - 'a'] ++;
 			ch2[s2.charAt(i) - 'a'] ++;
	    }
 		if (Arrays.equals(ch1, ch2)) return true;

 		for (int i = l1; i < l2; i ++) {
 			ch2[s2.charAt(i) - 'a'] ++;
 			ch2[s2.charAt(i - l1) - 'a'] --;
		    if (Arrays.equals(ch1, ch2)) return true;
	    }
 		return false;
	}
}
