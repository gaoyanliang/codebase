package com.yanliang.algo.offer.stack;

import java.util.Stack;

/**
 * 剑指 Offer II 039. 直方图最大矩形面积 https://leetcode-cn.com/problems/0ynMMM/
 *
 * <p>给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * <p>求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * <p>示例 1:
 *
 * <p>输入：heights = [2,1,5,6,2,3] 输出：10 解释：最大的矩形为图中红色区域，面积为 10
 *
 * <p>示例 2：
 *
 * <p>输入： heights = [2,4] 输出： 4
 *
 * <p>提示：
 *
 * <p>1 <= heights.length <=105 0 <= heights[i] <= 104
 */
public class LargestRectangleArea {

    public static void main(String[] args) {
        int[] heights1 = {3, 2, 5, 4, 6, 1, 4, 2};
        int[] heights2 = {2, 1, 5, 6, 2, 3};
        int[] heights3 = {2, 4};

        System.out.println(largestRectangleArea(heights1));
        System.out.println(largestRectangleArea(heights2));
        System.out.println(largestRectangleArea(heights3));
    }

    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = -1;
        // 栈中存元素下标，保证对应下标所对应的高度递增
        for (int i = 0; i < len; i++) {
            while (!stack.peek().equals(-1) && heights[i] <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }

        while (!stack.peek().equals(-1)) {
            int height = heights[stack.pop()];
            int width = len - stack.peek() - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }
}
