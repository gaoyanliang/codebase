package com.yanliang.algo.leetcode.array;

/**
 * @author yanliang
 * @date 2020/10/3022:25
 */
public class IslandPerimeter_463 {

    public static void main(String[] args) {
        int[][] grid = {{0, 1}};
        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(int[][] grid) {
        int len_r = grid.length;
        int len_c = grid[0].length;

        int ans = 0;
        for (int i = 0; i < len_r; i++) {
            for (int j = 0; j < len_c; j++) {
                if (grid[i][j] == 1) {
                    // 上
                    if (i == 0 || grid[i - 1][j] == 0) {
                        ans++;
                    }
                    // 下
                    if (i == len_r - 1 || grid[i + 1][j] == 0) {
                        ans++;
                    }
                    // 左
                    if (j == 0 || grid[i][j - 1] == 0) {
                        ans++;
                    }
                    // 右
                    if (j == len_c - 1 || grid[i][j + 1] == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
