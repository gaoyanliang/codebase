package com.yanliang.codebase.sort;

import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 *
 * @author yanliang
 */
public class FindKthLargestLC215 {

	public static void main(String[] args) {
		int[] nums = {3,2,1,5,6,4};
		int[] nums1 = {3,2,3,1,2,4,5,5,6};
		System.out.println(findKthLargest(nums, 2));
		System.out.println(findKthLargest(nums1, 4));
	}

	public static int findKthLargest(int[] nums, int k) {
		int len = nums.length;
		if (k > len) return -1;

		int l = 0, r = len - 1;
		int target = k - 1;
		while (true) {
			int index = partition(nums, l, r);
			if (index == target) return nums[index];
			else if (index > target) r = index - 1;
			else l = index + 1;
		}
	}

	public static int partition(int[] nums, int left, int right) {
		int tmp = nums[right];
		int j = left - 1;
		for (int i = left; i < right; i ++) {
			if (nums[i] >= tmp) {
				j ++;
				swap(nums, i, j);
			}
		}
		swap(nums, ++ j, right);
		return j;
	}

	/**
	 * 交换对应下标的数据
	 * @param nums
	 * @param a
	 * @param b
	 */
	public static void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
