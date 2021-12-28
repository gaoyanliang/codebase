package com.yanliang.algo.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * <p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * <p>示例:
 *
 * <p>给定 nums = [2, 7, 11, 15], target = 9
 *
 * <p>因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 *
 * @author yanliang
 * @date 11/19/2020 9:43 AM
 */
public class TwoSum_1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(twoSum(nums, 9)[0]);
        System.out.println(twoSum(nums, 9)[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        // key: val value: index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
