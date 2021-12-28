package com.yanliang.algo.leetcode.string;

/**
 * 32. 最长有效括号 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * <p>示例 1：
 *
 * <p>输入：s = "(()" 输出：2 解释：最长有效括号子串是 "()" 示例 2：
 *
 * <p>输入：s = ")()())" 输出：4 解释：最长有效括号子串是 "()()" 示例 3：
 *
 * <p>输入：s = "" 输出：0
 *
 * @author yanliang
 */
public class LongestValidParentheses_32 {

    public static void main(String[] args) {
        String s = "(()";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        int len = s.length();
        int left = 0, right = 0, max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) max = Math.max(max, left * 2);
            else if (right > left) left = right = 0;
        }

        left = 0;
        right = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') left++;
            else right++;

            if (left == right) max = Math.max(max, left * 2);
            else if (right > left) left = right = 0;
        }

        return max;
    }
}
