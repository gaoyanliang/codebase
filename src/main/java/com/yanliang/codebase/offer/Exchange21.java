package com.yanliang.codebase.offer;

public class Exchange21 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(exchange(nums));
    }

    public static int[] exchange(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (nums[l] % 2 != 0) {
                l ++;
            }
            while (nums[r] % 2 == 0) {
                r --;
            }
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
        return nums;
    }
}
