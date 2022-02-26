package com.yanliang.algo.offer.array;

/**
 * 剑指 Offer II 068. 查找插入位置 给定一个排序的整数数组 nums 和一个整数目标值 target ，请在数组中找到 target
 * ，并返回其下标。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * <p>请必须使用时间复杂度为 O(log n) 的算法。
 *
 * <p>示例 1:
 *
 * <p>输入: nums = [1,3,5,6], target = 5 输出: 2 示例 2:
 *
 * <p>输入: nums = [1,3,5,6], target = 2 输出: 1 示例 3:
 *
 * <p>输入: nums = [1,3,5,6], target = 7 输出: 4 示例 4:
 *
 * <p>输入: nums = [1,3,5,6], target = 0 输出: 0 示例 5:
 *
 * <p>输入: nums = [1], target = 0 输出: 0
 *
 * <p>提示:
 *
 * <p>1 <= nums.length <= 104 -104 <= nums[i] <= 104 nums 为无重复元素的升序排列数组 -104 <= target <= 104
 *
 * @author yanliang
 */
public class SearchInsert {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, 0));
        System.out.println(searchInsert(nums, 7));
        System.out.println(searchInsert(nums, 4));
    }

    public static int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r + l) >> 1;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                if (mid == 0 || target > nums[mid - 1]) return mid;
                r = mid - 1;
            }
        }
        return nums.length;
    }
}
