package com.yanliang.algo.offer.integer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 007. 数组中和为 0 的三个数 https://leetcode-cn.com/problems/1fGaJU/
 *
 * <p>给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。
 *
 * <p>示例 1：
 *
 * <p>输入：nums = [-1,0,1,2,-1,-4] 输出：[[-1,-1,2],[-1,0,1]] 示例 2：
 *
 * <p>输入：nums = [] 输出：[] 示例 3：
 *
 * <p>输入：nums = [0] 输出：[]
 *
 * <p>提示：
 *
 * <p>0 <= nums.length <= 3000 -105 <= nums[i] <= 105
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 0, 2, 2};
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums1));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums.length < 3) return res;

        // 先排序，保证数组有序
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2) {
            threeSum(nums, i, res);

            int tmp = nums[i];
            while (i < nums.length && nums[i] == tmp) {
                i++;
            }
        }

        return res;
    }

    public static void threeSum(int[] nums, int i, List<List<Integer>> res) {
        int j = i + 1;
        int k = nums.length - 1;
        while (j < k) {
            int sum = nums[i] + nums[j] + nums[k];
            if (sum == 0) {
                res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                int tmp = nums[j];
                while (j < k && nums[j] == tmp) {
                    j++;
                }
            } else if (sum > 0) {
                k--;
            } else {
                j++;
            }
        }
    }
}
