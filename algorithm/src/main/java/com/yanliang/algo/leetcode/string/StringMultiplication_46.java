package com.yanliang.algo.leetcode.string;

import java.util.Scanner;

/**
 * 43. 字符串相乘 https://leetcode-cn.com/problems/multiply-strings/
 *
 * <p>https://labuladong.gitee.io/algo/4/32/135/
 *
 * <p>给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * <p>注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * <p>示例 1:
 *
 * <p>输入: num1 = "2", num2 = "3" 输出: "6" 示例 2:
 *
 * <p>输入: num1 = "123", num2 = "456" 输出: "56088"
 *
 * <p>提示：
 *
 * <p>1 <= num1.length, num2.length <= 200 num1 和 num2 只能由数字组成。 num1 和 num2 都不包含任何前导零，除了数字0本身。
 *
 * @author yanliang
 */
public class StringMultiplication_46 {

    public static void main(String[] args) {
        int n = 5;
        Scanner sc = new Scanner(System.in);
        while (n-- > 0) {
            String num1 = sc.nextLine();
            String num2 = sc.nextLine();
            System.out.println(stringMul(num1, num2));
        }
    }

    public static String stringMul(String num1, String num2) {
        if (num1.length() > num2.length()) {
            return stringMul(num2, num1);
        }

        int n1 = num1.length();
        int n2 = num2.length();
        int[] res = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                int sum = a * b + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        while (i < res.length) {
            sb.append(res[i++]);
        }
        if (sb.length() == 0) sb.append(0);
        return sb.toString();
    }
}
