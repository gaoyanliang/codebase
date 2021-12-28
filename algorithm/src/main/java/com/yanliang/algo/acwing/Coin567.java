package com.yanliang.algo.acwing;

import java.util.Scanner;

/** @author yanliang */
public class Coin567 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(coin(n, m));
    }

    public static int coin(int n, int m) {
        if (m < n) return 1;
        int ans = m / n;
        return m % n == 0 ? ans : ans + 1;
    }
}
