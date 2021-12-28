package com.yanliang.algo.acwing;

import java.util.Scanner;

/** @author yanliang */
public class BalloonGame570 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = scanner.nextInt();
        }
        System.out.println(balloonGame(m, colors));
        scanner.close();
    }

    /**
     * @param m 颜色数量
     * @return
     */
    public static int balloonGame(int m, int[] colors) {
        int[] ba = new int[m];
        int time = 0, ans = Integer.MAX_VALUE, len = colors.length;

        for (int i = 0, j = 0; i < len; i++) {
            if (colors[i] == 0) continue;

            if (time < m) {
                ba[colors[i] - 1]++;
                if (ba[colors[i] - 1] == 1) time++;
            }
            while (j <= i && time == m) {
                if (colors[j] == 0) {
                    j++;
                    continue;
                }
                ans = Math.min(ans, i - j + 1);
                ba[colors[j] - 1]--;
                if (ba[colors[j] - 1] == 0) time--;
                j++;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
