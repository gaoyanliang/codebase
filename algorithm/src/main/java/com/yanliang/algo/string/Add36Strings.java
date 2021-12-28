package com.yanliang.algo.string;

import java.util.Stack;

/** @author yanliang */
public class Add36Strings {
    public static void main(String[] args) {
        System.out.println(add36Strings("1bbbb", "2xxxxxx"));
        System.out.println(add36Strings("1bbbb", "2xxxxxx"));
    }

    public static String add36Strings(String num1, String num2) {
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        int a = 0, b = 0, f = 0, sum = 0;
        Stack<Character> stack = new Stack<>();
        while (l1 >= 0 || l2 >= 0) {
            a = l1 >= 0 ? getInt(num1.charAt(l1)) : 0;
            b = l2 >= 0 ? getInt(num2.charAt(l2)) : 0;
            sum = a + b + f;
            stack.push(getChar(sum % 36));
            f = sum / 36;
            l1--;
            l2--;
        }
        if (f != 0) {
            stack.push(getChar(f));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static char getChar(int num) {
        if (num < 10) {
            return (char) (num + '0');
        }
        return (char) (num - 10 + 'a');
    }

    public static int getInt(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        }
        return ch - 'a' + 10;
    }

    // ---------------------------------------

    public static String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        int f = 0;
        StringBuilder sb = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int a = l1 >= 0 ? charToInt(num1.charAt(l1)) : 0;
            int b = l2 >= 0 ? charToInt(num2.charAt(l2)) : 0;
            int sum = a + b + f;
            sb.append(intToChar(sum % 36));
            f = sum / 36;
            l1--;
            l2--;
        }
        if (f != 0) sb.append(intToChar(f));
        sb.reverse();
        return sb.toString();
    }

    public static int charToInt(char ch) {
        if (ch >= '0' && ch <= '9') return ch - '0';
        return ch - 'a' + 10;
    }

    public static char intToChar(int n) {
        if (n < 10) return (char) (n + '0');
        return (char) (n - 10 + 'a');
    }
}
