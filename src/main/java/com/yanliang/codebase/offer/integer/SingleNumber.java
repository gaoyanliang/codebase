package com.yanliang.codebase.offer.integer;

/**
 * 剑指 Offer II 004. 只出现一次的数字    https://leetcode-cn.com/problems/WGki4K/
 *
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,100]
 * 输出：100
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {1,2,4,8,16};
        singleNumber(nums);
    }

    public static int singleNumber(int[] nums) {
        int len = nums.length;
        int[] times = new int[32];
        // 将十进制的整数转换为二进制，相同位上的数字相加
        // 如果将出现3次的数字单独拿出来，这些出现3次的数字的任意第i个数位之和都能被3整除
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < 32; j ++) {
                times[j] += (nums[i] >> (31 - j)) & 1;
            }
        }

        int res = 0;
        for (int i = 0; i < 32; i ++) {
            res = (res << 1) + times[i] % 3;
        }
        return res;
    }
}
