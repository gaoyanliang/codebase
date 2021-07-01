package com.yanliang.codebase.leetcode.array;

import java.util.stream.IntStream;

/**
 * @author yanliang
 */
public class SearchMatrix_74 {

	public static void main(String[] args) {

		IntStream.rangeClosed(1, 5).forEach((i) -> {
			System.out.println(i);
		});

		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		System.out.println(searchMatrix(matrix, 3));
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;
		int l = 0, r = m * n - 1;
		while (l < r) {
			int mid = (r + l + 1) >> 1;
			if ( matrix[mid / n][mid % n] > target) {
				r = mid - 1;
			} else {
				l = mid;
			}
		}
		return matrix[r / n][r % n] == target;
	}
}
