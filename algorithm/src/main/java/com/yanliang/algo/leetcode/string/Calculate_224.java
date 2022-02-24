package com.yanliang.algo.leetcode.string;

import java.util.*;

/**
 * 224. 基本计算器 https://leetcode-cn.com/problems/basic-calculator/submissions/
 *
 * <p>https://leetcode-cn.com/problems/basic-calculator/solution/shuang-zhan-jie-jue-tong-yong-biao-da-sh-olym/
 *
 * <p>给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * <p>注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * <p>示例 1：
 *
 * <p>输入：s = "1 + 1" 输出：2 示例 2：
 *
 * <p>输入：s = " 2-1 + 2 " 输出：3 示例 3：
 *
 * <p>输入：s = "(1+(4+5+2)-3)+(6+8)" 输出：23
 *
 * <p>提示：
 *
 * <p>1 <= s.length <= 3 * 105 s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 s 表示一个有效的表达式 '+' 不能用作一元运算(例如， "+1" 和
 * "+(2 + 3)" 无效) '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的) 输入中不存在两个连续的操作符 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 * @author yanliang
 */
public class Calculate_224 {

    public static void main(String[] args) {
        int n = 3;
        Scanner sc = new Scanner(System.in);
        while (n-- > 0) {
            String s = sc.nextLine();
            System.out.println(calculate(s));
        }
    }

    public static int calculate(String s) {
        // 定义运算优先级
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('%', 2);
        map.put('^', 3);

        // 去除空格
        s = s.replaceAll(" ", "");

        Deque<Integer> nums = new ArrayDeque<>();
        // 防止首个数字前存在运算符
        nums.addLast(0);
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                ops.addLast(c);
                // 遇到右括号，开始计算括号内的算式，计算完成移除左括号
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peekLast() != '(') {
                    cal(nums, ops);
                }
                ops.pollLast();
            } else if (isNum(c)) {
                int num = 0;
                int j = i;
                while (j < s.length()) {
                    c = s.charAt(j);
                    if (isNum(c)) {
                        num = num * 10 + (c - '0');
                        j++;
                    } else {
                        break;
                    }
                }
                nums.addLast(num);
                i = j - 1;
                // + - * / % ^
            } else {
                // (-1) -- > (0-1)
                if (i > 0 && (s.charAt(i - 1) == '(')) {
                    nums.addLast(0);
                }
                while (!ops.isEmpty() && ops.peekLast() != '(') {
                    char preOps = ops.peekLast();
                    if (map.get(preOps) >= map.get(c)) {
                        cal(nums, ops);
                    } else {
                        break;
                    }
                }
                ops.addLast(c);
            }
        }
        while (!ops.isEmpty()) {
            cal(nums, ops);
        }
        return nums.pollLast();
    }

    // 计算逻辑
    public static void cal(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        char op = ops.pollLast();
        int b = nums.pollLast(), a = nums.pollLast();
        int res = 0;
        if (op == '+') {
            res = a + b;
        } else if (op == '-') {
            res = a - b;
        } else if (op == '*') {
            res = a * b;
        } else if (op == '/') {
            res = a / b;
        } else if (op == '%') {
            res = a % b;
        } else if (op == '^') {
            res = a ^ b;
        }
        nums.addLast(res);
    }

    // 判断是否是数字
    public static boolean isNum(char ch) {
        return Character.isDigit(ch);
    }
}
