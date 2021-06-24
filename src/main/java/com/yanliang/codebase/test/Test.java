package com.yanliang.codebase.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;

/**
 * @author yanliang
 * @date 2021/2/415:18
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(topKFrequent(new int[]{1,1,1,2,2,3}, 2));
	}

	static Random random = new Random();
	public static int[] topKFrequent(int[] nums, int k) {
		quickSort(nums, 0, nums.length - 1);

		Set<Integer> set = new HashSet<>();
		for (int i = nums.length - 1; i >= 0; i --) {
			if (set.size() < k) set.add(nums[i]);
		}
		int[] ans = new int[k];
		int j = 0;
		for (int i: set) {
			ans[j ++] = i;
		}
		return ans;
	}

	public static void quickSort(int[] nums, int l, int r) {
		if (l < r) {
			int index = partition(nums, l, r);
			quickSort(nums, l, index - 1);
			quickSort(nums, index + 1, r);
		}
	}

	public static int partition(int[] nums, int l, int r) {
		int index = random.nextInt(r - l) + l;
		swap(nums, r, index);
		int tmp = nums[r], j = l - 1;
		for (int i = l; i < r; i ++) {
			if (nums[i] < tmp) {
				swap(nums, i, ++ j);
			}
		}
		swap(nums, r, ++ j);
		return j;
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
