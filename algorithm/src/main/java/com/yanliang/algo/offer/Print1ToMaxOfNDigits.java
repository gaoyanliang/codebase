package com.yanliang.algo.offer;

import java.util.Arrays;

/**
 * 剑指offer 12 打印1到最大的n位数
 *
 * @author yanliang
 */
public class Print1ToMaxOfNDigits {

    public static void main(String[] args) {
        print1ToMaxOfNDigits(3);
    }

    public static void print1ToMaxOfNDigits(int n) {
        char[] nums = new char[n];
        Arrays.fill(nums, '0');
        System.out.println("0");
        while (!incr(nums, n)) {
            printNum(nums, n);
        }
    }

    public static void printNum(char[] nums, int n) {
        int i = 0;
        while (i < n && nums[i] == '0') {
            i++;
        }
        while (i < n) {
            System.out.print(nums[i]);
            i++;
        }
        System.out.println();
    }

    public static boolean incr(char[] nums, int n) {
        boolean isOverflow = false;
        int nTakeOver = 0;
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum = nTakeOver + nums[i] - '0';
            if (i == n - 1) sum++;
            if (sum >= 10) {
                if (i == 0) {
                    isOverflow = true;
                    break;
                }
                nums[i] = (char) (sum - 10 + '0');
                nTakeOver = 1;
            } else {
                nums[i] = (char) (sum + '0');
            }
        }
        return isOverflow;
    }
}
