package com.yanliang.algo.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 659. 分割数组为连续子序列
 * https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/submissions/
 *
 * <p>https://labuladong.gitee.io/algo/4/32/132/ 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3
 * 的子序列，其中每个子序列都由连续整数组成。
 *
 * <p>如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * <p>示例 1：
 *
 * <p>输入: [1,2,3,3,4,5] 输出: True 解释: 你可以分割出这样两个连续子序列 : 1, 2, 3 3, 4, 5 示例 2：
 *
 * <p>输入: [1,2,3,3,4,4,5,5] 输出: True 解释: 你可以分割出这样两个连续子序列 : 1, 2, 3, 4, 5 3, 4, 5 示例 3：
 *
 * <p>输入: [1,2,3,4,4,5] 输出: False
 *
 * <p>提示：
 *
 * <p>1 <= nums.length <= 10000
 *
 * @author yanliang
 */
public class IsPossible_659 {

    //    [1,2,3,3,4,5]
    //            [1,2,3,3,4,4,5,5]
    //            [1,2,3,4,4,5]

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 3, 4, 5};
        int[] nums2 = {1, 2, 3, 3, 4, 4, 5, 5};
        int[] nums3 = {1, 2, 3, 4, 4, 5};
        System.out.println(isPossible(nums1));
        System.out.println(isPossible(nums2));
        System.out.println(isPossible(nums3));
    }

    public static boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();

        // 统计每个数字的个数
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            // == 0 说明之前命中 else if ，已经被用到序列中
            if (freq.getOrDefault(num, 0) == 0) continue;

            // need 大于 0，说明可以续到之前的序列中
            if (need.getOrDefault(num, 0) > 0) {
                freq.put(num, freq.getOrDefault(num, 0) - 1);
                need.put(num, need.getOrDefault(num, 0) - 1);

                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);
                // 新开一个序列
            } else if (freq.getOrDefault(num, 0) > 0
                    && freq.getOrDefault(num + 1, 0) > 0
                    && freq.getOrDefault(num + 2, 0) > 0) {
                freq.put(num, freq.getOrDefault(num, 0) - 1);
                freq.put(num + 1, freq.getOrDefault(num + 1, 0) - 1);
                freq.put(num + 2, freq.getOrDefault(num + 2, 0) - 1);

                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}
