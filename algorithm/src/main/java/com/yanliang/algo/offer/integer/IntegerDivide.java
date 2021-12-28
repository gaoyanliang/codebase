package com.yanliang.algo.offer.integer;

/**
 * 剑指 Offer II 001. 整数除法 https://leetcode-cn.com/problems/xoh6Oh/
 *
 * <p>给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 *
 * <p>注意：
 *
 * <p>整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 假设我们的环境只能存储 32
 * 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
 *
 * <p>示例 1：
 *
 * <p>输入：a = 15, b = 2 输出：7 解释：15/2 = truncate(7.5) = 7 示例 2：
 *
 * <p>输入：a = 7, b = -3 输出：-2 解释：7/-3 = truncate(-2.33333..) = -2 示例 3：
 *
 * <p>输入：a = 0, b = 1 输出：0 示例 4：
 *
 * <p>输入：a = 1, b = 1 输出：1
 *
 * <p>提示:
 *
 * <p>-231 <= a, b <= 231 - 1 b != 0
 */
public class IntegerDivide {

    public static void main(String[] args) {
        System.out.println(divide(15, 2));
        System.out.println(divide(7, -3));
        System.out.println(divide(0, 1));
        System.out.println(divide(1, 1));
    }

    /**
     * 整数除法
     *
     * @param a 被除数 dividend
     * @param b 除数 divisor
     * @return
     */
    public static int divide(int a, int b) {
        // 唯一可能溢出的情况
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;

        int negative = 2;
        if (a > 0) {
            a = -a;
            negative--;
        }
        if (b > 0) {
            b = -b;
            negative--;
        }

        int res = divideCore(a, b);
        return negative == 1 ? -res : res;
    }

    public static int divideCore(int dividend, int divisor) {
        int res = 0;
        // 注意a, b都是负数，所以a <= b就是还可以继续除
        while (dividend <= divisor) {
            int value = divisor;
            int quotient = 1;
            // 0xc0000000 = Integer.MIN_VALUE / 2
            while (value >= 0xc0000000 && dividend <= value + value) {
                quotient += quotient;
                value += value;
            }
            res += quotient;
            dividend -= value;
        }
        return res;
    }
}
