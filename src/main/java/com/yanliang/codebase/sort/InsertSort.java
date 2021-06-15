package com.yanliang.codebase.sort;

/**
 * 插入排序
 * 思路：每次将一个数字插入一个有序的数组里，成为一个长度更长的有序数组，有限次操作以后，数组整体有序。
 *
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 *
 * @author yanliang
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] nums = {9,8,6,3,25,7,4,1,6,8,1};
		insertSort(nums);
		print(nums);
	}

	public static void print(int[] nums) {
		for (int i = 0; i < nums.length; i ++) {
			System.out.print(nums[i]);
			System.out.print(" ");
		}
	}

	// 插入排序：稳定排序，在接近有序的情况下，表现优异
	public static void insertSort(int[] nums) {
		int len = nums.length;
		// 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
		for (int i = 1; i < len; i ++) {
			// 先暂存这个元素，然后之前元素逐个后移，留出空位
			int tmp = nums[i], j = i;
			// 注意边界 j > 0
			while (j > 0 && nums[j - 1] > tmp) {
				nums[j] = nums[j - 1];
				j --;
			}
			nums[j] = tmp;
		}
	}

}
