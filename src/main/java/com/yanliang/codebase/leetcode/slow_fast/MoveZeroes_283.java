package com.yanliang.codebase.leetcode.slow_fast;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author yanliang
 * @date 2020/10/821:48
 */
public class MoveZeroes_283 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 移动零
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        // 去除 nums 中的所有 0
        // 返回去除 0 之后的数组长度
        int len = removeElement(nums, 0);
        // 将 p 之后的所有元素赋值为 0
        for (int i = len; i < nums.length; i ++) {
            nums[i] = 0;
        }
    }

    /**
     * 移除元素
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
                slow ++;
            }
            fast ++;
        }
        return slow;
    }

// ----------------------------二刷移动零---------------------------------

    public void moveZeroes_2(int[] nums) {
        int len = removeElement_2(nums, 0);

        for (int i = len; i < nums.length; i ++) {
            nums[i] = 0;
        }
    }

    public int removeElement_2(int[] nums, int val) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int fast = 0, slow = 0;
        while (fast < len) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow;
    }

}
