package com.yanliang.algo.acwing;

import java.util.Scanner;

/** @author yanliang */
public class Series568 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n > 0) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            System.out.println(series(l, r));
            n--;
        }
        scanner.close();
    }

    public static int series(int l, int r) {
        int k = r - l + 1;
        int ans = k / 2;
        if ((l & 1) == 0) {
            ans *= -1;
        }
        if ((k & 1) == 1) {
            if ((r & 1) == 1) ans -= r;
            else ans += r;
        }
        return ans;
    }
}
