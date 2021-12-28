package com.yanliang.algo.sort;

/**
 * @author yanliang
 * @date 11/16/2020 9:31 AM
 */
public class Sort {

    public static void main(String[] args) {
        int[] nums = {9, 3, 5, 6, 8, 1, 3, 6, 4, 7, 5, 1, 5, 1, 2, 35, 7, 5, 2, 1, 55, 33, 88, 99};
        sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println(topK(nums, 2));
    }

    public static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    /**
     * 快速排序核心逻辑
     *
     * @param nums
     * @param left
     * @param right
     */
    public static void sort(int[] nums, int left, int right) {
        if (left >= right) return;

        // 通过交换元素构建分界点索引 p
        int p = partition(nums, left, right);

        // 现在 nums[lo..p-1] 都小于 nums[p]，且 nums[p+1..hi] 都大于 nums[p]
        sort(nums, left, p - 1);
        sort(nums, p + 1, right);
    }

    public static int partition(int[] nums, int left, int right) {
        if (left == right) return left;
        // j = hi + 1 因为 while 中会先执行 --
        int i = left, j = right + 1;
        // 将 nums[lo] 作为默认分界点 pivot
        int p = nums[left];

        while (true) {
            while (nums[++i] < p) {
                if (i == right) break;
            }
            while (nums[--j] > p) {
                if (j == left) break;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, j, left);
        return j;
    }

    /**
     * 交换数据
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static int topK(int[] nums, int k) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        k = nums.length - k;
        while (left <= right) {
            int p = partition(nums, left, right);
            if (k < p) {
                left = p + 1;
            } else if (k > p) {
                right = p - 1;
            } else {
                return nums[p];
            }
        }
        return -1;
    }
}
