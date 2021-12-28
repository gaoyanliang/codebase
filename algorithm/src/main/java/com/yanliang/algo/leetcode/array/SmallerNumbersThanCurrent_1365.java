package com.yanliang.algo.leetcode.array;

/**
 * @author yanliang
 * @date 10/26/2020 12:57 PM
 */
public class SmallerNumbersThanCurrent_1365 {

    public static void main(String[] args) {
        int[] A = {7, 7, 7, 7, 7, 7};
        int[] ints = smallerNumbersThanCurrent(A);

        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();

        int[] B = {1, 2, 3, 4, 5};
        int[] ints1 = smallerNumbersThanCurrent(B);

        for (int i = 0; i < ints1.length; i++) {
            System.out.print(ints1[i] + " ");
        }
        System.out.println();
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] count = new int[101];
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            count[nums[i]]++;
        }

        for (int i = 1; i < 101; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < len; i++) {
            ans[i] = nums[i] == 0 ? 0 : count[nums[i] - 1];
        }
        return ans;
    }
}
