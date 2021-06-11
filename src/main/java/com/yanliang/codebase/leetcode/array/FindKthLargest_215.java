package com.yanliang.codebase.leetcode.array;

/**
 * @author yanliang
 * @date 2021/2/1922:59
 */
public class FindKthLargest_215 {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest(nums, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int target = len - k;
        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target){
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    public static int partition(int[] nums, int left, int right) {
        int tmp = nums[right];
        int j = left - 1;
        for (int i = left; i < right; i ++) {
            if (nums[i] <= tmp) {
                ++ j;
                swap(nums, j, i);
            }
        }
        swap(nums, ++ j, right);
        return j;
    }

    public static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

}
