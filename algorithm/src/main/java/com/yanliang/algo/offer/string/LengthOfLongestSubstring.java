package com.yanliang.algo.offer.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 016. 不含重复字符的最长子字符串 https://leetcode-cn.com/problems/wtcaE1/
 *
 * <p>给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 *
 * <p>示例 1:
 *
 * <p>输入: s = "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。 示例 2:
 *
 * <p>输入: s = "bbbbb" 输出: 1 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。 示例 3:
 *
 * <p>输入: s = "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke"
 * 是一个子序列，不是子串。 示例 4:
 *
 * <p>输入: s = "" 输出: 0
 *
 * <p>提示：
 *
 * <p>0 <= s.length <= 5 * 104 s 由英文字母、数字、符号和空格组成
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int res = 0, l = 0, r = 0;
        Map<Character, Integer> windows = new HashMap<>();

        while (r < s.length()) {
            char in = s.charAt(r++);
            windows.put(in, windows.getOrDefault(in, 0) + 1);
            while (windows.get(in) > 1) {
                char out = s.charAt(l++);
                windows.put(out, windows.get(out) - 1);
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
