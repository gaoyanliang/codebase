package com.yanliang.algo.offer.integer;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组 https://leetcode-cn.com/problems/ZVAVXX/
 *
 * <p>给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 *
 * <p>示例 1:
 *
 * <p>输入: nums = [10,5,2,6], k = 100 输出: 8 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5],
 * [5,2], [2,6], [5,2,6]。 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。 示例 2:
 *
 * <p>输入: nums = [1,2,3], k = 0 输出: 0
 *
 * <p>提示:
 *
 * <p>1 <= nums.length <= 3 * 104 1 <= nums[i] <= 1000 0 <= k <= 106
 */
public class NumSubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int l = 0, sum = 1;
        for (int r = 0; r < nums.length; r++) {
            sum *= nums[r];
            while (sum >= k && l <= r) {
                sum /= nums[l++];
            }
            /**
             * right - left + 1 的理解：
             *
             * <p>比如某次遍历符合题意的子数组为 ABCX，那么在该条件下符合条件的有X，CX，BCX，ABCX共四个（可以进行多个例子，发现个数符合right-left+1）
             * 我们可能会有疑问：AB，BC也算，为什么不算进去？ 记住一点我们是以最右边的X为必要条件，进行计算符合条件的子数组，否则会出现重复的！
             * 比如在X为右侧边界时（ABCX），我们把BC算进去了，可是我们在C为最右侧时（ABC），BC已经出现过，我们重复加了BC这个子数组两次！
             * 换言之，我们拆分子数组时，让num[right]存在，能避免重复计算。
             */
            count += r >= l ? r - l + 1 : 0;
        }
        return count;
    }
}
