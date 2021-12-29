package com.yanliang.algo.offer.stack;

import java.util.Stack;

/**
 * 剑指 Offer II 040. 矩阵中最大的矩形 https://leetcode-cn.com/problems/PLYXKQ/
 *
 * <p>给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。
 *
 * <p>注意：此题 matrix 输入格式为一维 01 字符串数组。
 *
 * <p>示例 1：
 *
 * <p>输入：matrix = ["10100","10111","11111","10010"] 输出：6 解释：最大矩形如上图所示。 示例 2：
 *
 * <p>输入：matrix = [] 输出：0 示例 3：
 *
 * <p>输入：matrix = ["0"] 输出：0 示例 4：
 *
 * <p>输入：matrix = ["1"] 输出：1 示例 5：
 *
 * <p>输入：matrix = ["00"] 输出：0
 *
 * <p>提示：
 *
 * <p>rows == matrix.length cols == matrix[0].length 0 <= row, cols <= 200 matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalRectangle {

    public static void main(String[] args) {
        String[] matrix1 = {"10100", "10111", "11111", "10010"};
        String[] matrix2 = {"0"};
        String[] matrix3 = {"1"};
        String[] matrix4 = {"00"};

        System.out.println(maximalRectangle(matrix1));
        System.out.println(maximalRectangle(matrix2));
        System.out.println(maximalRectangle(matrix3));
        System.out.println(maximalRectangle(matrix4));
    }

    public static int maximalRectangle(String[] matrix) {
        if (matrix.length == 0) return 0;
        int res = 0;

        int len = matrix[0].length();
        int[] h = new int[len];
        for (String s : matrix) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) - '0' == 0) {
                    h[i] = 0;
                } else {
                    h[i] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(h));
        }
        return res;
    }

    /**
     * {@link LargestRectangleArea}
     *
     * @param h
     * @return
     */
    public static int largestRectangleArea(int[] h) {
        int len = h.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int res = -1;

        for (int i = 0; i < len; i++) {
            while (!stack.peek().equals(-1) && h[i] <= h[stack.peek()]) {
                int height = h[stack.pop()];
                int width = i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }

        while (!stack.peek().equals(-1)) {
            int height = h[stack.pop()];
            int width = len - stack.peek() - 1;
            res = Math.max(res, height * width);
        }

        return res;
    }
}
