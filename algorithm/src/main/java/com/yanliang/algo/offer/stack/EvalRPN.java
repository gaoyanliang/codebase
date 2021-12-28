package com.yanliang.algo.offer.stack;

import java.util.Stack;

/**
 * 剑指 Offer II 036. 后缀表达式 根据 逆波兰表示法，求该后缀表达式的计算结果。
 *
 * <p>有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * <p>说明：
 *
 * <p>整数除法只保留整数部分。 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * <p>示例 1：
 *
 * <p>输入：tokens = ["2","1","+","3","*"] 输出：9 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9 示例 2：
 *
 * <p>输入：tokens = ["4","13","5","/","+"] 输出：6 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6 示例 3：
 *
 * <p>输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"] 输出：22 解释：
 * 该算式转化为常见的中缀算术表达式为： ((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = ((10 * (6 / (12 * -11))) + 17) + 5 =
 * ((10 * (6 / -132)) + 17) + 5 = ((10 * 0) + 17) + 5 = (0 + 17) + 5 = 17 + 5 = 22
 */
public class EvalRPN {

    public static void main(String[] args) {
        String[] token1 = {"2", "1", "+", "3", "*"};
        String[] token2 = {"4", "13", "5", "/", "+"};
        String[] token3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(token1));
        System.out.println(evalRPN(token2));
        System.out.println(evalRPN(token3));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> numStack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                case "-":
                case "*":
                case "/":
                    int b = numStack.pop();
                    int a = numStack.pop();
                    numStack.push(cal(a, b, tokens[i]));
                    break;
                default:
                    numStack.push(Integer.parseInt(tokens[i]));
            }
        }
        return numStack.pop();
    }

    public static int cal(int a, int b, String str) {
        switch (str) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }
}
