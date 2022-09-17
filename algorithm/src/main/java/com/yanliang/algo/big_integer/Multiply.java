package com.yanliang.algo.big_integer;

import java.util.Scanner;

/**
 *43. 字符串相乘  https://leetcode.cn/problems/multiply-strings/
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 * @author yanliang
 */
public class Multiply {

    private static final String ZERO = "0";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String num1 = sc.next();
            String num2 = sc.next();
            System.out.println(multiply(num1, num2));
        }
    }

    public static String multiply(String num1, String num2) {
        if (ZERO.equals(num1) || ZERO.equals(num2)) return ZERO;

        char[] num1_ch = num1.toCharArray();
        char[] num2_ch = num2.toCharArray();

        // 存储结果
        int[] num = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i --) {
            for (int j = num2.length() - 1; j >= 0; j --) {
                // 计算乘积，并把乘积放在 res 对应的位置， 暂时不考虑进位
                num[i + j + 1] += (num1_ch[i] - '0') * (num2_ch[j] - '0');
            }
        }

        // 进位
        int carry = 0;
        // 从个位再次遍历，如果上一次遍历中两数乘积为两位数，进位并叠加到前面一位
        for (int i = num.length - 1; i >= 0; i --) {
            int sum = num[i] + carry;
            num[i] = sum % 10;
            carry = sum / 10;
        }

        int i = 0;
        for (i = 0; i < num.length - 1; i ++) {
            if (num[i] != 0) break;
        }

        StringBuilder sb = new StringBuilder();
        for (; i < num.length; i ++) {
            sb.append(num[i]);
        }

        return sb.toString();
    }
}
