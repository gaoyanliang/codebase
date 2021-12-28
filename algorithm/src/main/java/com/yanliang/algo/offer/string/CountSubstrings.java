package com.yanliang.algo.offer.string;

/**
 * 剑指 Offer II 020. 回文子字符串的个数 https://leetcode-cn.com/problems/a7VOhD/
 *
 * <p>给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 *
 * <p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * <p>示例 1：
 *
 * <p>输入：s = "abc" 输出：3 解释：三个回文子串: "a", "b", "c" 示例 2：
 *
 * <p>输入：s = "aaa" 输出：6 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * <p>提示：
 *
 * <p>1 <= s.length <= 1000 s 由小写英文字母组成
 */
public class CountSubstrings {

    public static void main(String[] args) {
        System.out.println(manachar("abc"));
        System.out.println(manachar("aaa"));
    }

    public static int manachar(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        sb.append("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        sb.append("@");
        s = sb.toString();

        int[] f = new int[s.length()];
        int id = 0, mx = 0, res = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            f[i] = mx >= i ? Math.min(mx - i, f[2 * id - i]) : 1;

            while (s.charAt(i + f[i]) == s.charAt(i - f[i])) f[i]++;

            if (i + f[i] > mx) {
                id = i;
                mx = id + f[i];
            }

            res += f[i] / 2;
        }
        return res;
    }
}
