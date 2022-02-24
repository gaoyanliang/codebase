package com.yanliang.algo.leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

/** @author yanliang */
public class IsValid_20 {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        char[] ch = s.toCharArray();
        int n = ch.length;
        for (int i = 0; i < n; i++) {
            if (ch[i] == '(' || ch[i] == '[' || ch[i] == '{') {
                stack.addLast(ch[i]);
            } else if (ch[i] == ')') {
                if (stack.isEmpty()) return false;
                if (stack.peekLast() != '(') return false;
                else stack.pollLast();
            } else if (ch[i] == ']') {
                if (stack.isEmpty()) return false;
                if (stack.peekLast() != '[') return false;
                else stack.pollLast();
            } else if (ch[i] == '}') {
                if (stack.isEmpty()) return false;
                if (stack.peekLast() != '{') return false;
                else stack.pollLast();
            }
        }
        return stack.isEmpty();
    }
}
