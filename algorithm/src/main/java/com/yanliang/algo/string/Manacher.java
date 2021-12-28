package com.yanliang.algo.string;

/** @author yanliang */
public class Manacher {

    public static void main(String[] args) {
        System.out.println(manacher("abbahopxpo"));
    }

    public static int manacher(String s) {
        int len = s.length();
        if (len == 0) return 0;
        if (len == 1) return 1;

        // 1. 改造字符串 abc --> $#a#b#c#&
        char[] tmp = new char[len * 2 + 4];
        tmp[0] = '$';
        tmp[1] = '#';
        int i = 0, j = 2;
        for (; i < len; i++) {
            tmp[j++] = s.charAt(i);
            tmp[j++] = '#';
        }
        tmp[j++] = '&';

        int mx = 0, id = 0;
        int max = -1;
        int[] dp = new int[j];
        for (i = 1; i < j; i++) {
            if (i < mx) {
                dp[i] = Math.max(dp[2 * id - i], mx - i);
            } else {
                dp[i] = 1;
            }

            while (tmp[i - dp[i]] == tmp[i + dp[i]]) {
                dp[i]++;
            }

            if (mx < i + dp[i]) {
                id = i;
                mx = i + dp[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
