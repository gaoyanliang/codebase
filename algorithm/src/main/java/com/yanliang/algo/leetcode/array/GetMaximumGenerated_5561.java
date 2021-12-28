package com.yanliang.algo.leetcode.array;

/**
 * @author yanliang
 * @date 11/8/2020 10:49 AM
 */
public class GetMaximumGenerated_5561 {

    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(7));
        System.out.println(getMaximumGenerated(2));
        System.out.println(getMaximumGenerated(3));
    }

    public static int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }
        int[] res = new int[101];
        int max = 1, index = 1;
        res[0] = 0;
        res[1] = 1;
        while (index * 2 + 1 <= n) {
            res[index * 2] = res[index];
            max = Math.max(max, res[index * 2]);
            res[index * 2 + 1] = res[index] + res[index + 1];
            max = Math.max(max, res[index * 2 + 1]);
            index++;
        }
        return max;
    }
}
