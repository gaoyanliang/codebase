package com.yanliang.codebase.offer.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 014. 字符串中的变位词    https://leetcode-cn.com/problems/MPnaiL/
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class CheckInclusion {

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int l = 0, r = 0;
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i ++) {
            char ch = s1.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int count = need.size();

        Map<Character, Integer> windows = new HashMap<>();

        while (r < s2.length()) {
            char ch = s2.charAt(r ++);
            if (need.containsKey(ch)) {
                windows.put(ch, windows.getOrDefault(ch, 0) + 1);
                if(windows.get(ch).equals(need.get(ch))) {
                    count --;
                }
            }

            // 相等判断必须用 equals，不能用 == ，否则 101 案例跑不过
            while (r - l >= s1.length()) {
                if (count == 0) return true;
                char del = s2.charAt(l ++);
                if (need.containsKey(del)) {
                    if (windows.get(del).equals(need.get(del))) count ++;
                    windows.put(del, windows.get(del) - 1);
                }
            }
        }
        return false;
    }
}
