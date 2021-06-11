package com.yanliang.codebase.leetcode;

/**
 * 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
 *
 * 输入: 2
 * 输出: 2
 * 解释：有2种方案。分别是0->1->0和0->9->0
 *
 * @author yanliang
 */
public class BackToOrigin {

	public static void main(String[] args) {
		System.out.println(backToOrigin(2, 10));
	}

	public static int backToOrigin(int n, int spots) {
		int[][] dp = new int[n + 1][spots];
		// 走 0 步，走到 0
		dp[0][0] = 1;
		// 走 1 步， 走到 1 或者 spots - 1
		dp[1][1] = 1;
		dp[1][spots - 1] = 1;
		for (int i = 1; i <= n; i ++) {
			for (int j = 0; j < spots; j ++) {
				dp[i][j] = dp[i - 1][(j - 1 + spots) % spots] + dp[i - 1][(j + 1) % spots];
			}
		}
		return dp[n][0];
	}
}
