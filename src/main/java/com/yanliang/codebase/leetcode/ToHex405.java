package com.yanliang.codebase.leetcode;

/**
 * @author yanliang
 */
public class ToHex405 {

	public static void main(String[] args) {
		System.out.println(toHex(-1));
	}

	public static String toHex(int num) {
		final char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		StringBuilder sb = new StringBuilder();
		do {
			sb.append(chars[num & 15]);
			num >>>= 4;
		} while (num > 0);
		return sb.reverse().toString();
	}
}
