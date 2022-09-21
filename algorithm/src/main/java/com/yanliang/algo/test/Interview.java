package com.yanliang.algo.test;

/**
 * @author yanliang
 */
public class Interview {


    public int[] sort(int[] nums) {
        int len = nums.length;

        int l = 0, r = len - 1;
        while (l < r) {
            while (l < r && nums[l] % 2 != 0) {
                l ++;
            }

            while (l < r && nums[r] % 2 == 0) {
                r --;
            }

            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;

            l ++;
            r --;

        }
        return nums;
    }
}
