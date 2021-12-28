package com.yanliang.algo.leetcode.string;

/**
 * @author yanliang
 * @date 2020/10/1716:26
 */
public class MaxDepth {

    public static void main(String[] args) {
        // 期待3
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
        // 期待3
        System.out.println(maxDepth("(1)+((2))+(((3)))"));
        // 期待1
        System.out.println(maxDepth("1+(2*3)/(2-1)"));
        // 期待 0
        System.out.println("1");
    }

    public static int maxDepth(String s) {
        int len = s.length();
        int curDepth = 0;
        int max = 0;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                curDepth++;
                max = Math.max(curDepth, max);
            }
            if (ch == ')') {
                curDepth--;
            }
        }
        return max;
    }
}
