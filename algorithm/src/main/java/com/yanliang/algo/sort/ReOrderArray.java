package com.yanliang.algo.sort;

/**
 * NC77 调整数组顺序使奇数位于偶数前面(一)
 * https://www.nowcoder.com/practice/ef1f53ef31ca408cada5093c8780f44b?tpId=117&tqId=37776&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 *
 * 描述
 * 输入一个长度为 n 整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前面部分，所有的偶数位于数组的后面部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * 数据范围：0 \le n \le 50000≤n≤5000，数组中每个数的值 0 \le val \le 100000≤val≤10000
 * 要求：时间复杂度 O(n)，空间复杂度 O(n)
 * 进阶：时间复杂度 O(n^2) 空间复杂度 O(1)O(1)
 * 示例1
 * 输入：
 * [1,2,3,4]
 * 返回值：
 * [1,3,2,4]
 * 示例2
 * 输入：
 * [2,4,6,5,7]
 * 返回值：
 * [5,7,2,4,6]
 * 示例3
 * 输入：
 * [1,3,5,6,7]
 * 返回值：
 * [1,3,5,7,6]
 * @author yanliang
 */
public class ReOrderArray {


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型一维数组
     */
    public int[] reOrderArray (int[] nums) {
        // write code here
        int len = nums.length;

        int j = 0, k = 0, tmp = 0;
        for (int i = 0; i < len; i ++) {
            if (nums[i] % 2 == 1) {
                tmp = nums[i];
                k = i;
                while (k > j) {
                    nums[k] = nums[k - 1];
                    k --;
                }
                nums[j ++] = tmp;
            }
        }

        return nums;
    }


    public int[] reOrderArray1(int[] nums) {
        // write code here
        int len = nums.length;

        int[] orderArray = new int[len];
        int l = 0, r = len - 1;
        int i = 0, j = len - 1;
        while (i < len && j >= 0) {
            if (nums[i] % 2 == 1) {
                orderArray[l ++] = nums[i];
            }
            i ++;

            if (nums[j] % 2 == 0) {
                orderArray[r --] = nums[j];
            }
            j --;
        }

        return orderArray;
    }

}
