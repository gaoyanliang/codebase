package com.yanliang.codebase.leetcode.array;

import java.util.Stack;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 * 提示：
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * @author yanliang
 */
public class FindCircleNum_547 {

	public static void main(String[] args) {

		int[][] a = {{1,1,0},{1,1,0},{0,0,1}};
		int[][] b = {{1,0,0},{0,1,0},{0,0,1}};
		int[][] c = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};

		System.out.println(findCircleNum(a));  // 2
		System.out.println(findCircleNum(b));  // 3
		System.out.println(findCircleNum(c));  // 1


	}

	public static int findCircleNum(int[][] isConnected) {
		int n = isConnected.length;
		int res = 0;
		for (int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j ++) {
				if (isConnected[i][j] == 1) {
					res ++;
					bfs(isConnected, i, j, n);
				}
			}
		}
		return res;
	}

	public static void bfs(int[][] isConnected, int i, int j, int len) {
		Stack<Integer> stack = new Stack<>();
		stack.add(j);
		isConnected[i][j] = 0;
		isConnected[j][i] = 0;
		while (!stack.isEmpty()) {
			int k = stack.pop();
			isConnected[k][k] = 0;
			for (int l = 0; l < len; l ++) {
				if (isConnected[k][l] == 1) {
					stack.add(l);
					isConnected[k][l] = 0;
					isConnected[l][k] = 0;
				}
			}
		}
	}
}
