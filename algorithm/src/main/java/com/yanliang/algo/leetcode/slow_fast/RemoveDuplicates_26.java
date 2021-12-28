package com.yanliang.algo.leetcode.slow_fast;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * <p>不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * <p>
 *
 * <p>示例 1:
 *
 * <p>给定数组 nums = [1,1,2],
 *
 * <p>函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * <p>你不需要考虑数组中超出新长度后面的元素。 示例 2:
 *
 * <p>给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * <p>函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * <p>你不需要考虑数组中超出新长度后面的元素。
 *
 * <p>说明:
 *
 * <p>为什么返回数值是整数，但输出的答案是数组呢?
 *
 * <p>请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * <p>你可以想象内部操作如下:
 *
 * <p>// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝 int len = removeDuplicates(nums);
 *
 * <p>// 在函数里修改输入数组对于调用者是可见的。 // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。 for (int i = 0; i < len; i++) {
 * print(nums[i]); }
 *
 * @author yanliang
 * @date 2020/10/821:10
 */
public class RemoveDuplicates_26 {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 4};

        // 期待2
        System.out.println(removeDuplicates(nums1));
        // 期待5
        System.out.println(removeDuplicates(nums2));
    }

    /**
     * 删除排序数组中的重复项🔁
     *
     * @param nums
     * @return 移除后数组的新长度
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int slow = 0, fast = 0;
        while (fast < len) {
            if (nums[slow] != nums[fast]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }
}
