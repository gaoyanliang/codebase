package com.yanliang.algo.sort;

import java.util.Random;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * 215. 数组中的第K个最大元素 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * <p>请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * <p>示例 1:
 *
 * <p>输入: [3,2,1,5,6,4] 和 k = 2 输出: 5 示例 2:
 *
 * <p>输入: [3,2,3,1,2,4,5,5,6] 和 k = 4 输出: 4
 *
 * <p>提示：
 *
 * <p>1 <= k <= nums.length <= 104 -104 <= nums[i] <= 104
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @author yanliang
 */
public class FindKthLargestLC215 {

    static Random random = new Random();

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int[] nums1 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(nums, 2));
        System.out.println(findKthLargest(nums1, 4));
    }

    public static int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int quickSort(int[] nums, int l, int r, int k){
        int index = partition(nums, l, r);
        if (index == k) return nums[index];
        if (k < index) return quickSort(nums, l, index - 1, k);
        else return quickSort(nums, index + 1, r, k);
    }

    public static int partition(int[] nums, int l, int r) {
        int index = random.nextInt(r - l + 1) + l;
        swap(nums, index, r);
        int k = l - 1, tmp = nums[r];
        for (int i = l; i < r; i ++) {
            if (nums[i] < tmp) swap(nums, i, ++ k);
        }
        swap(nums, r, ++ k);
        return k;
    }

    /**
     * 交换对应下标的数据
     *
     * @param nums
     * @param l
     * @param r
     */
    public static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
