package com.yanliang.codebase.leetcode;

/**
 * 556. 下一个更大元素 III
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 *
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：21
 * 示例 2：
 *
 * 输入：n = 21
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 * @author yanliang
 */
public class NextGreaterElement_556 {

	public static void main(String[] args) {
		System.out.println(nextGreaterElement(12334455));
		System.out.println(nextGreaterElement(12334465));
		System.out.println(nextGreaterElement(158476531));

	}

	public static int nextGreaterElement(int n) {
		char[] chs = String.valueOf(n).toCharArray();
		int len = chs.length;
		int i = len - 2;
		// 找到可以交换的数字下标
		while (i >= 0 && chs[i] >= chs[i + 1]) {
			i --;
		}
		if (i < 0) return -1;

		int j = len - 1;
		while (j > i && chs[j] <= chs[i]) {
			j --;
		}

		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
		reserve(chs, i + 1, len - 1);
		int res = 0;
		try {
			res = Integer.parseInt(String.valueOf(chs));
		} catch(Exception e) {
			return -1;
		}
		return res;
	}

	public static void reserve(char[] chs, int start, int end) {
		int len = end - start + 1;
		for (int i = 0; i < len / 2; i ++) {
			char tmp = chs[start + i];
			chs[start + i] = chs[end - i];
			chs[end - i] = tmp;
		}
	}
}
