package com.yanliang.algo.leetcode.slow_fast;

/**
 * @author yanliang
 * @date 2020/10/821:22
 */
public class RemoveElement_27 {

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 2, 3};
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};

        // 期待 2
        System.out.println(removeElement(nums1, 3));
        // 期待 5
        System.out.println(removeElement(nums2, 2));
    }

    /**
     * 移除元素
     *
     * @param nums
     * @param val
     * @return 移除后数组的新长度
     */
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int len = nums.length, slow = 0, fast = 0;
        while (fast < len) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
