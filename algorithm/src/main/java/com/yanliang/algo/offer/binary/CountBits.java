package com.yanliang.algo.offer.binary;

/**
 * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数 https://leetcode-cn.com/problems/w3tCBm/ 给定一个非负整数 n ，请计算 0 到 n
 * 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 *
 * <p>示例 1:
 *
 * <p>输入: n = 2 输出: [0,1,1] 解释: 0 --> 0 1 --> 1 2 --> 10 示例 2:
 *
 * <p>输入: n = 5 输出: [0,1,1,2,1,2] 解释: 0 --> 0 1 --> 1 2 --> 10 3 --> 11 4 --> 100 5 --> 101
 *
 * <p>说明 :
 *
 * <p>0 <= n <= 105
 *
 * <p>进阶:
 *
 * <p>给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？ 要求算法的空间复杂度为 O(n) 。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。
 */
public class CountBits {

    /**
     * [0,1] [0,1,1] [0,1,1,2] [0,1,1,2,1] [0,1,1,2,1,2]
     *
     * @param args
     */
    public static void main(String[] args) {
        print(countBits(1));
        print(countBits(2));
        print(countBits(3));
        print(countBits(4));
        print(countBits(5));
    }

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
