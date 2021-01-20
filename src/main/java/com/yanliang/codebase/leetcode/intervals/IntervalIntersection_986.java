package com.yanliang.codebase.leetcode.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 区间列表交集：给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。返回这两个区间列表的交集。
 *
 *
 *
 * @author yanliang
 * @date 10/13/2020 9:51 AM
 */
public class IntervalIntersection_986 {

	public static void main(String[] args) {
		int[][] a = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
		int[][] b = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

		System.out.println(intervalIntersection(a, b));

		int[][] a1 = {{4, 6}, {7, 8}, {10, 17}};
		int[][] b1 = {{5, 10}};
		System.out.println(intervalIntersection(a1, b1));
	}

	public static int[][] intervalIntersection(int[][] A, int[][] B) {
		// 校验输入
		if (A.length == 0 || B.length == 0) {
			return new int[0][0];
		}

		int indexA = 0, indexB = 0;
		List<int[]> res = new ArrayList<>();
		while (indexA < A.length && indexB < B.length) {
			int[] a = A[indexA];
			int[] b = B[indexB];
			int[] tmp = new int[2];

			// 相交
			if (b[1] >= a[0] && a[1] >= b[0]) {
				tmp[0] = Math.max(a[0], b[0]);
				tmp[1] = Math.min(a[1], b[1]);
				res.add(tmp);
			}

			// 移动
			if (b[1] < a[1]) {
				indexB ++;
			} else {
				indexA ++;
			}
		}
		return res.toArray(new int[res.size()][2]);
	}
}
