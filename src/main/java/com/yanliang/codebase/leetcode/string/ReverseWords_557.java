package com.yanliang.codebase.leetcode.string;

/**
 * @author yanliang
 * @date 2020/10/2623:25
 */
public class ReverseWords_557 {

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int left = 0, right = 0;
        StringBuilder ans = new StringBuilder();
        while (right < s.length()) {
            while (right < s.length() && s.charAt(right) != ' ') {
                right ++;
            }
            ans.append(reverse(s, left, right - 1));
            right += 1;
            left = right;
            if (right < s.length()) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    public static String reverse(String s, int left, int right) {
        StringBuilder sb = new StringBuilder();
        while (left <= right)  {
            sb.append(s.charAt(right));
            right --;
        }
        return sb.toString();
    }
}
