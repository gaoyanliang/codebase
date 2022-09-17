package com.yanliang.algo.big_integer;

import java.util.Scanner;

/**
 * 415. 字符串相加  https://leetcode.cn/problems/add-strings/
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 *
 *
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * @author yanliang
 */
public class Add {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String a = sc.next();
            if ("exit".equals(a)) {
                break;
            }
            String b = sc.next();
            System.out.println(add(a, b));
        }
    }

    public static String add(String a, String b) {
        if (a.length() == 0) return b;
        if (b.length() == 0) return a;

        int i = a.length() - 1;
        int j = b.length() - 1;

        // 进位
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int m = i >= 0 ? a.charAt(i --) - '0' : 0;
            int n = j >= 0 ? b.charAt(j --) - '0' : 0;
            int sum = (m + n + carry) % 10;
            sb.append((char) (sum + '0'));
            carry = (m + n + carry) / 10;
        }
        if (carry > 0) sb.append(carry);

        // 反转字符串，去除前导零
        sb = sb.reverse();
        int k = 0;
        for (k = 0; k < sb.length() - 1; k ++) {
            if (sb.charAt(k) != '0') break;
        }
        return sb.substring(k);
    }
}
