package com.yanliang.algo.interview;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 模拟计算器 https://labuladong.gitee.io/algo/4/33/128/
 *
 * 我们最终要实现的计算器功能如下：
 *
 * 1、输入一个字符串，可以包含 + - * /、数字、括号以及空格，你的算法返回运算结果。
 *
 * 2、要符合运算法则，括号的优先级最高，先乘除后加减。
 *
 * 3、除号是整数除法，无论正负都向 0 取整（5/2=2，-5/2=-2）。
 *
 * 4、可以假定输入的算式一定合法，且计算过程不会出现整型溢出，不会出现除数为 0 的意外情况。
 *
 * 比如输入如下字符串，算法会返回 9：
 *
 *   3 * (2 - 6 / (3 - 7))
 * = 3 * (2 - 6 / (-4))
 * = 3 * (2 - (-1))
 * = 9
 *
 * @author yanliang
 */
public class Calculate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(calculate(sc.nextLine()));
    }

    public static int calculate(String s) {
        // 将算式中的所有字符入队
        Deque<Character> queue = new LinkedList<>();
        for (char ch: s.toCharArray()) {
            if (ch != ' ') {
                queue.addLast(ch);
            }
        }

        return calculate(queue);
    }

    public static int calculate(Deque<Character> queue) {
        int num = 0;
        char opt = '+';
        Deque<Integer> stack = new LinkedList<Integer>();
        while(!queue.isEmpty()) {
            char ch = queue.removeFirst();
            if (isNum(ch)) {
                // ch - '0' 加括号，防止溢出
                num = num * 10 + (ch - '0');
            }

            // 遇见左括号，进入递归
            if (ch == '(') {
                num = calculate(queue);
            }

            if (!isNum(ch) || queue.isEmpty()) {
                int pre;
                // 注意：这里判断的是上一个运算符
                switch (opt) {
                    case '+':
                        stack.addLast(num);
                        break;
                    case '-':
                        stack.addLast(-num);
                        break;
                    case '*':
                        pre = stack.removeLast();
                        stack.addLast(num * pre);
                        break;
                    case '/':
                        pre = stack.removeLast();
                        stack.addLast(pre / num);
                        break;
                }
                // 归零
                opt = ch;
                num = 0;
            }

            // 遇见右括号，结束
            if (ch == ')') break;
        }

        // 计算结果
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.removeLast();
        }
        return res;
    }

    public static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
