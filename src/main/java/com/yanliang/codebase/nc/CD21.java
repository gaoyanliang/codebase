package com.yanliang.codebase.nc;

import java.util.Scanner;

/**
 * CD21 计算数组的小和
 *
 * 数组小和的定义如下：
 * 例如，数组s = [1, 3, 5, 2, 4, 6]，在s[0]的左边小于或等于s[0]的数的和为0；在s[1]的左边小于或等于s[1]的数的和为1；在s[2]的左边小于或等于s[2]的数的和为1+3=4；在s[3]的左边小于或等于s[3]的数的和为1；
 * 在s[4]的左边小于或等于s[4]的数的和为1+3+2=6；在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15。所以s的小和为0+1+4+1+6+15=27
 * 给定一个数组s，实现函数返回s的小和
 *
 * [要求]
 * 时间复杂度为O(nlogn)O(nlogn)，空间复杂度为O(n)O(n)
 *
 * 输入描述：
 * 第一行有一个整数N。表示数组长度
 * 接下来一行N个整数表示数组内的数
 *
 * 示例1
 * 输入：
 * 6
 * 1 3 5 2 4 6
 *
 * 输出：
 * 27
 *
 * 自测地址： https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469?tpId=101&tqId=33089&tPage=1&rp=1&ru=%2Fta%2Fprogrammer-code-interview-guide
 * @author yanliang
 */
public class CD21 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] s = new int[n];
		for (int i = 0; i < n; i ++) {
			s[i] = sc.nextInt();
		}
		System.out.println(mergeSort(s, 0, n - 1));
	}

	public static long mergeSort(int[] s, int l, int r) {
		if (l < r) {
			int mid = l + (r - l) / 2;
			return mergeSort(s, l, mid) + mergeSort(s, mid + 1, r) + merge(s, l, mid, r);
		}
		return 0;
	}

	public static long merge(int[] s, int l, int mid, int r) {
		int[] tmp = new int[r - l + 1];
		int i = l, j = mid + 1, index = 0;
		long res = 0;
		while (i <= mid && j <= r) {
			if (s[i] <= s[j]) {
				res += (r - j + 1) * s[i];
				tmp[index ++] = s[i ++];
			} else {
				tmp[index ++] = s[j ++];
			}
		}
		while (i <= mid) tmp[index ++] = s[i ++];
		while (j <= r) tmp[index ++] = s[j ++];
		for (int k = 0; k < index; k ++) {
			s[l + k] = tmp[k];
		}
		return res;
	}
}
