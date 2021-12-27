package com.yanliang.codebase.offer.stack;

import java.util.Stack;

/**
 * 剑指 Offer II 038. 每日温度    https://leetcode-cn.com/problems/iIQa4I/
 *
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] nums1 = {73,74,75,71,69,72,76,73};
        int[] nums2 = {30,40,50,60};
        int[] nums3 = {30,60,90};
        int[] ints = dailyTemperatures(nums1);
        int[] ints1 = dailyTemperatures(nums2);
        int[] ints2 = dailyTemperatures(nums3);
        System.out.println();
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i ++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index  = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }
}
