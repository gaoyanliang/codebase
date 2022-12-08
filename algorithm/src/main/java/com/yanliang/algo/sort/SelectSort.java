package com.yanliang.algo.sort;

/**
 * 选择排序
 *
 * 思路：每一轮选取未排定的部分中最小的部分交换到未排定部分的最开头，经过若干个步骤，就能排定整个数组。即：先选出最小的，再选出第 2 小的，以此类推。 算法思想
 * 1：贪心算法：每一次决策只看当前，当前最优，则全局最优。注意：这种思想不是任何时候都适用。
 *
 * <p>算法思想 2：减治思想：外层循环每一次都能排定一个元素，问题的规模逐渐减少，直到全部解决，即「大而化小，小而化了」。运用「减治思想」很典型的算法就是大名鼎鼎的「二分查找」。
 *
 * <p>时间复杂度：O(N^2)，这里 N 是数组的长度； 空间复杂度：O(1)，使用到常数个临时变量。
 *
 * @author yanliang
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] nums = {9, 8, 6, 3, 25, 7, 4, 1, 6, 8, 1};
        selectSort(nums);
        print(nums);
    }

    public static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
    }

    public static void selectSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int index = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }
            swap(nums, index, i);
        }
    }

    public static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
