package com.yanliang.algo.offer;

import java.util.Scanner;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回
 * false。假设输入的数组的任意两个数字都互不相同。
 *
 * <p>参考以下这颗二叉搜索树：
 *
 * <p>5 / \ 2 6 / \ 1 3
 *
 * <p>输入: [1,6,3,2,5] 输出: false
 *
 * <p>输入: [1,3,2,6,5] 输出: true
 *
 * <p>[4, 8, 6, 12, 16, 14, 10] true [1,6,3,2,5] false [1,3,2,6,5] true
 *
 * @author yanliang
 */
public class VerifyPostorder33 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
        }
        System.out.println(verifyPostorder(s, 0, n - 1));
    }

    public static boolean verifyPostorder(int[] s, int l, int r) {
        if (l >= r) return true;
        int index = l;
        while (s[index] < s[r]) index++;
        int mid = index;
        while (s[index] > s[r]) index++;
        return index == r && verifyPostorder(s, l, mid - 1) && verifyPostorder(s, mid, r - 1);
    }
}
