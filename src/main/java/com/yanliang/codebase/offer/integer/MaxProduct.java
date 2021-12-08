package com.yanliang.codebase.offer.integer;

/**
 * 剑指 Offer II 005. 单词长度的最大乘积
 * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 *
 * 示例 1:
 *
 * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
 * 示例 2:
 *
 * 输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 *
 * 输入: words = ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *
 * 提示：
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class MaxProduct {

    public static void main(String[] args) {
        String[] words1 = {"abcw","baz","foo","bar","fxyz","abcdef"};
        String[] words2 = {"a","ab","abc","d","cd","bcd","abcd"};
        String[] words3 = {"a","aa","aaa","aaaa"};
        System.out.println(maxProduct(words1));
        System.out.println(maxProduct(words2));
        System.out.println(maxProduct(words3));
    }

    public static int maxProduct(String[] words) {
        int len = words.length;
        int[] flag = new int[len];
        // 将每个单词中的每个字符，用二进制的 1/0 来表示是否存在
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < words[i].length(); j ++) {
                flag[i] |= (1 << (words[i].charAt(j) - 'a'));
            }
        }

        int res = 0;
        // 如果两个字符串中不包含重复字符，则两个整数 & 操作之后应该为 0
        for (int i = 0; i < len; i ++) {
            for (int j = i; j < len; j ++) {
                if ((flag[i] & flag[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
