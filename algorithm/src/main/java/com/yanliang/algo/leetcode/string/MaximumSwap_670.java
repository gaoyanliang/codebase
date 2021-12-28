package com.yanliang.algo.leetcode.string;

/**
 * 670. 最大交换 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * <p>示例 1 :
 *
 * <p>输入: 2736 输出: 7236 解释: 交换数字2和数字7。 示例 2 :
 *
 * <p>输入: 9973 输出: 9973 解释: 不需要交换。 注意:
 *
 * <p>给定数字的范围是 [0, 108]
 *
 * @author yanliang
 */
public class MaximumSwap_670 {

    public static void main(String[] args) {

        System.out.println(maximumSwap(2736));
        System.out.println(maximumSwap(12344));
        System.out.println(maximumSwap(9976));
    }

    public static int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[] chars = s.toCharArray();

        int right = chars.length - 1;
        int left = right - 1;
        int sl = 0, sr = 0;
        while (left >= 0) {
            if (chars[left] < chars[right]) {
                sl = left;
                sr = right;
            } else if (chars[left] > chars[right]) {
                right = left;
            }
            left--;
        }
        char tem = chars[sl];
        chars[sl] = chars[sr];
        chars[sr] = tem;
        return Integer.parseInt(String.valueOf(chars));
    }
}
