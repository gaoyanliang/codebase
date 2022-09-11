package com.yanliang.algo.sliding_window;

/**
 * 424. 替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 *
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 * @author yanliang
 */
public class LongestRepeatingCharacterReplacementLC424 {

    int[] map = new int[26];

    public int characterReplacement(String s, int k) {
        char[] chs = s.toCharArray();

        int left = 0, right = 0;
        int ret = 0;

        while (right < s.length()) {
            // 符合要求，继续向右扩散
            while (right < s.length() && canReplaceOk(k)) {
                ret = Math.max(ret, right - left);
                map[s.charAt(right ++) - 'A'] ++;
            }
            // 边界判断
            if (right == s.length() && canReplaceOk(k)) {
                ret = Math.max(ret, right - left);
                break;
            }

            // 不符合要求时，开始从左向右收缩
            while (left < right && !canReplaceOk(k)) {
                map[s.charAt(left ++) - 'A'] --;
            }
        }
        return ret;
    }

    // 判断窗口内的字符是否符合要求
    public boolean canReplaceOk(int k) {
        int total = 0;
        int max = 0;
        for (int num: map) {
            total += num;
            max = Math.max(max, num);
        }

        if (total == 0) return true;

        return total - max <= k;
    }
}
