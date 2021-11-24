package com.yanliang.codebase.leetcode.array;

/**
 * 498. 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 *
 *
 * 示例:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * 输出:  [1,2,4,7,5,3,6,8,9]
 *
 * @author yanliang
 */
public class FindDiagonalOrder_498 {

	public static void main(String[] args) {
		int[][] mat = {{2,3}};

		System.out.println(findDiagonalOrder(mat));
	}

	public static int[] findDiagonalOrder(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		int index = 0, i = 0;
		int[] res = new int[m * n];

		int x = 0, y = 0;
		while (i < m + n) {
			x = (i < m) ? i : m -1;
			y = i - x;
			while (x >= 0 && y < n) {
				res[index ++] = mat[x][y];
				x --;
				y ++;
			}
			i ++;

			y = (i < n) ? i : n - 1;
			x = i - y;
			while (x < m && y >= 0) {
				res[index ++] = mat[x][y];
				x ++;
				y --;
			}
			i ++;
		}
		return res;
	}
}
