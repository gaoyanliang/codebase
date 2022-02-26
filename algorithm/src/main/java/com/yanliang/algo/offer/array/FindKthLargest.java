package com.yanliang.algo.offer.array;

import java.util.Random;

/** @author yanliang */
public class FindKthLargest {
    static Random random = new Random();

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int index = partition(nums, l, r);
            if (index == k - 1) {
                return nums[index];
            } else if (index < k) {
                l = index + 1;
            } else {
                r = index - 1;
            }
        }
        return -1;
    }

    public static int partition(int[] nums, int l, int r) {
        int index = l + random.nextInt(r - l + 1);
        swap(nums, index, r);
        int i = l - 1, tmp = nums[r];
        for (int j = l; j < r; j++) {
            if (nums[j] >= tmp) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, ++i, r);
        return i;
    }

    public static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
