package com.yanliang.algo.tencent;

import java.util.Scanner;

/**
 * 腾讯 - 气球游戏
 *
 * 小Q在进行射击气球的游戏，如果小Q在连续T枪中打爆了所有颜色的气球，将得到一只QQ公仔作为奖励。（每种颜色的球至少被打爆一只）。
 *
 * 这个游戏中有m种不同颜色的气球，编号1到m。
 *
 * 小Q一共有n发子弹，然后连续开了n枪。
 *
 * 小Q想知道在这n枪中，打爆所有颜色的气球最少用了连续几枪？
 *
 * 输入格式
 * 第一行包含两个整数n和m。
 *
 * 第二行包含n个整数，分别表示每一枪打中的气球的颜色，0表示没打中任何颜色的气球。
 *
 * 输出格式
 * 一个整数表示小Q打爆所有颜色气球用的最少枪数。
 *
 * 如果小Q无法在这n枪打爆所有颜色的气球，则输出-1。
 *
 * 数据范围
 * 1≤n≤106,
 * 1≤m≤2000
 *
 * 输入样例：
 * 12 5
 * 2 5 3 1 3 2 4 1 0 5 4 3
 * 输出样例：
 * 6
 * 样例解释
 * 有五种颜色的气球，编号1到5。
 *
 * 游客从第二枪开始直到第七枪，这连续六枪打爆了5 3 1 3 2 4这几种颜色的气球，包含了从1到5的所有颜色，所以最少枪数为6。
 * @author yanliang
 */
public class BalloonGame {

    public static int search(int n, int m, int[] colors) {
        int left = 0, right = 0;
        int[] colorCnt = new int[m + 1];
        int num = 0;
        int ret = Integer.MAX_VALUE;
        while (right < n) {
            // 滑动窗口向右扩散
            int color = colors[right ++];
            if (color == 0) continue;
            colorCnt[color] ++;
            if (colorCnt[color] == 1) {
                num ++;
            }

            // 找到一个结果
            if (num == m) {
                ret = Math.min(ret, right - left);
            }

            // 逐渐缩小窗口
            while (num == m && left < right) {
                int i = colors[left ++];
                colorCnt[i] --;
                if (colorCnt[i] == 0) {
                    num --;
                }

                if (num == m) {
                    ret = Math.min(ret, right - left);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] colors = new int[n];

        for (int i = 0; i < n; i ++) {
            colors[i] = sc.nextInt();
        }

        System.out.println(search(n, m, colors));
    }
}
