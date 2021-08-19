package com.yanliang.codebase.dynamic;

/**
 * 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
 *
 * 输入: 2
 * 输出: 2
 * 解释：有2种方案。分别是0->1->0和0->9->0
 */
public class BackToOrigin {

    private static final int len = 10;
    private static final int step = 2;


    public static void main(String[] args) {
        System.out.println(backToOrigin());
    }

    public static int backToOrigin() {
        // dp[i][j]为从0点出发走i步到j点的方案数
        int[][] dp = new int[step + 1][len];

        dp[0][0] = 1;

        for (int i = 1; i <= step; i ++) {
            for (int j = 0; j < len; j ++) {
                dp[i][j] = dp[i - 1][(j - 1 + len) % len] + dp[i - 1][(j + 1) % len];
            }
        }
        return dp[step][0];
    }

}
