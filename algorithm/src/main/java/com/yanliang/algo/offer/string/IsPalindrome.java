package com.yanliang.algo.offer.string;

public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        // 全部变为小写字母
        s = s.toLowerCase();
        while (i < j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (!isNum(ch1) && !isCh(ch1)) {
                i++;
            } else if (!isNum(ch2) && !isCh(ch2)) {
                j--;
            } else {
                if (ch1 != ch2) return false;
                i++;
                j--;
            }
        }
        return true;
    }

    public static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isCh(char ch) {
        return ch >= 'a' && ch <= 'z';
    }
}
