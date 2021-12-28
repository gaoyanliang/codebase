package com.yanliang.algo.leetcode;

/** @author yanliang */
public class Add36Strings {

    public static void main(String[] args) {
        System.out.println(add36Strings("1b", "2x"));
    }

    public static String add36Strings(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        if (l1 < l2) return add36Strings(s2, s1);

        int i = l1 - 1, j = l2 - 1, f = 0, index = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || f != 0) {
            int a = i >= 0 ? getInt(s1.charAt(i)) : 0;
            int b = j >= 0 ? getInt(s1.charAt(j)) : 0;
            sb.append(getChar((a + b + f) % 36));
            f = (a + b + f) / 36;
            i--;
            j--;
        }

        return sb.toString();
    }

    public static int getInt(char ch) {
        if (ch >= '0' && ch <= '9') return ch - '0';
        else return 10 + ch - 'a';
    }

    public static char getChar(int num) {
        if (num < 10) return (char) ('0' + num);
        else return (char) (num - 10 + 'a');
    }
}
