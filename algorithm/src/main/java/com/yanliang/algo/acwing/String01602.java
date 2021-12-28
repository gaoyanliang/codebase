package com.yanliang.algo.acwing;

import java.util.Scanner;

/** @author yanliang */
public class String01602 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String string = scanner.nextLine();

        int len = string.length();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (string.charAt(i) == '1') {
                ans++;
            } else {
                ans--;
            }
        }
        System.out.println(Math.abs(ans));
        scanner.close();
    }
}
