package com.yanliang.algo.offer.binary;

/**
 * 二进制加法 https://leetcode-cn.com/problems/JFETK5/
 *
 * <p>给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 *
 * <p>输入为 非空 字符串且只包含数字 1 和 0。
 *
 * <p>示例1:
 *
 * <p>输入: a = "11", b = "10" 输出: "101" 示例2:
 *
 * <p>输入: a = "1010", b = "1011" 输出: "10101"
 *
 * <p>提示：
 *
 * <p>每个字符串仅由字符 '0' 或 '1' 组成。 1 <= a.length, b.length <= 10^4 字符串如果不是 "0" ，就都不含前导零。
 */
public class BinaryAddition {

    public static void main(String[] args) {
        System.out.println(addBinary("11", "10"));
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // 模拟十进制加法
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int z = x + y + carry;

            sb.append(z >= 2 ? z - 2 : z);
            carry = z >= 2 ? 1 : 0;
            i--;
            j--;
        }
        if (carry == 1) sb.append(carry);
        return sb.reverse().toString();
    }
}
