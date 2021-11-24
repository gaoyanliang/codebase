package com.yanliang.codebase.sort;

/**
 * @author yanliang
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] nums = {5,1,1,2,0,0};
		mergeSort(nums);
		System.out.println();
	}

	public static int[] mergeSort(int[] nums) {
		int len = nums.length;
		mergeSort(nums, 0, len - 1);
		return nums;
	}

	public static void mergeSort(int[] nums, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(nums, left, mid);
			mergeSort(nums, mid + 1, right);
			merge(nums, left, mid, right);
		}
	}

	public static void merge(int[] nums, int left, int mid, int right) {
		int[] tmp = new int[right - left + 1];
		int l1 = left, l2 = mid + 1, index = 0;
		while (l1 <= mid && l2 <= right) {
			if (nums[l1] < nums[l2]) tmp[index ++] = nums[l1 ++];
			else tmp[index ++] = nums[l2 ++];
		}
		while (l1 <= mid) tmp[index ++] = nums[l1 ++];
		while (l2 <= right) tmp[index ++] = nums[l2 ++];

		int i = 0;
		while (left <= right) {
			nums[left ++] = tmp[i ++];
		}
	}
}
