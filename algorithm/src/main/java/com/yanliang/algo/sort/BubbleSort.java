package com.yanliang.algo.sort;

/**
 * 冒泡排序
 * https://imageslr.com/2021/sort-algorithm.html
 * @author yanliang
 */
public class BubbleSort {

    public void bubbleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n-1; i++) { // i 表示已经排序的元素，排完 n-1 个后最后一个也不需要排了
            for (int j = 1; j < n-i; j++) {
                if (nums[j-1] > nums[j]) {
                    // swap(nums[j-1], nums[j]);
                }
            }
        }
    }
}
