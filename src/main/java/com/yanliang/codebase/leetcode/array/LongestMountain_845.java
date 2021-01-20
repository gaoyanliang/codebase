package com.yanliang.codebase.leetcode.array;

/**
 * 845. 数组中的最长山脉
 *
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 *
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 *
 * 如果不含有 “山脉” 则返回 0。
 *
 * @author yanliang
 * @date 2020/10/2523:51
 */
public class LongestMountain_845 {

    public static void main(String[] args) {
        int[] A = {2,1,4,7,3,2,5};
        System.out.println(longestMountain(A));

        int[] a = {2,2,2};
        System.out.println(longestMountain(a));

    }


    public static int longestMountain(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int len = A.length;
        int ans = 0;
        for (int i = 1; i < len - 1; i ++) {
            if (A[i] > A[i -1] && A[i] > A[i + 1]) {   // 找到山顶
                int left = i - 1;
                int right = i + 1;
                while (left > 0 && A[left] > A[left - 1]) left --;   // 寻找左山脚
                while (right < len - 1 && A[right] > A[right + 1]) right ++;   // 寻找右山脚
                ans = Math.max(ans, right - left + 1);
            }
        }
        return ans;
    }
}
