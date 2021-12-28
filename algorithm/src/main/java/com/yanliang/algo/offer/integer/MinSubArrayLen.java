package com.yanliang.algo.offer.integer;

/**
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组 https://leetcode-cn.com/problems/2VG8Kg/
 *
 * <p>给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * <p>找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
 * ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * <p>示例 1：
 *
 * <p>输入：target = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。 示例 2：
 *
 * <p>输入：target = 4, nums = [1,4,4] 输出：1 示例 3：
 *
 * <p>输入：target = 11, nums = [1,1,1,1,1,1,1,1] 输出：0
 *
 * <p>提示：
 *
 * <p>1 <= target <= 109 1 <= nums.length <= 105 1 <= nums[i] <= 105
 *
 * <p>进阶：
 *
 * <p>如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int l = 0, r = 0, sum = 0;
        for (r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
