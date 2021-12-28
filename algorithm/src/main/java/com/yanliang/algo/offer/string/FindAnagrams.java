package com.yanliang.algo.offer.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词 https://leetcode-cn.com/problems/VabMRr/
 *
 * <p>给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * <p>变位词 指字母相同，但排列不同的字符串。
 *
 * <p>示例 1:
 *
 * <p>输入: s = "cbaebabacd", p = "abc" 输出: [0,6] 解释: 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。 起始索引等于 6
 * 的子串是 "bac", 它是 "abc" 的变位词。 示例 2:
 *
 * <p>输入: s = "abab", p = "ab" 输出: [0,1,2] 解释: 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。 起始索引等于 1 的子串是 "ba",
 * 它是 "ab" 的变位词。 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
 *
 * <p>提示:
 *
 * <p>1 <= s.length, p.length <= 3 * 104 s 和 p 仅包含小写字母
 */
public class FindAnagrams {

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int r = 0, l = 0;
        int count = need.size();

        while (r < s.length()) {
            char in = s.charAt(r++);
            if (need.containsKey(in)) {
                windows.put(in, windows.getOrDefault(in, 0) + 1);
                if (windows.get(in).equals(need.get(in))) count--;
            }

            while (r - l >= p.length()) {
                if (count == 0) res.add(l);
                char out = s.charAt(l++);
                if (need.containsKey(out)) {
                    if (need.get(out).equals(windows.get(out))) count++;
                    windows.put(out, windows.get(out) - 1);
                }
            }
        }
        return res;
    }
}
