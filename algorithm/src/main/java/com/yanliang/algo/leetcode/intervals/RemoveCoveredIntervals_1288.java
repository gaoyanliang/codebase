package com.yanliang.algo.leetcode.intervals;

import java.util.Arrays;

/**
 * @author yanliang
 * @date 10/12/2020 9:09 AM
 */
public class RemoveCoveredIntervals_1288 {

    public static void main(String[] args) {
        int[][] intvs = {{1, 4}, {3, 6}, {2, 8}, {9, 12}, {10, 11}};
        // 期待 3
        System.out.println(removeCoveredIntervals(intvs));

        int[][] intvs2 = {{3, 10}, {4, 10}, {5, 11}};
        // 期待2
        System.out.println(removeCoveredIntervals(intvs2));
    }

    public static int removeCoveredIntervals(int[][] intvs) {
        // 排序：左边升序排，右边降序排
        Arrays.sort(
                intvs,
                (a, b) -> {
                    if (a[0] == b[0]) {
                        return b[1] - a[1];
                    }
                    return a[0] - b[0];
                });

        int left = intvs[0][0];
        int right = intvs[0][1];
        int res = 0;
        for (int i = 1; i < intvs.length; i++) {
            int[] intv = intvs[i];
            if (left <= intv[0] && right >= intv[1]) { // 找到相交区间，合并
                res++;
            } else if (right >= intv[0] && right <= intv[1]) { // 找到覆盖区间
                right = intv[1];
            } else if (right < intv[0]) { // 完全不相交，更新起点和终点
                left = intv[0];
                right = intv[1];
            }
        }
        return intvs.length - res;
    }
}
