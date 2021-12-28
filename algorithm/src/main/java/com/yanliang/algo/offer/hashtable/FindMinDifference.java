package com.yanliang.algo.offer.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 035. 最小时间差 https://leetcode-cn.com/problems/569nqc/
 *
 * <p>给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 * <p>示例 1：
 *
 * <p>输入：timePoints = ["23:59","00:00"] 输出：1 示例 2：
 *
 * <p>输入：timePoints = ["00:00","23:59","00:00"] 输出：0
 *
 * <p>提示：
 *
 * <p>2 <= timePoints <= 2 * 104 timePoints[i] 格式为 "HH:MM"
 */
public class FindMinDifference {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("23:59");
        list.add("00:00");
        System.out.println(findMinDifference(list));
        list.add("00:00");
        System.out.println(findMinDifference(list));
    }

    public static int findMinDifference(List<String> timePoints) {
        // 一天最多 1440 分钟, 如果超出，则至少存在两个相同时间
        if (timePoints.size() > 1440) return 0;
        boolean[] bo = new boolean[1440];
        for (String s : timePoints) {
            String[] strs = s.split(":");
            // 将当前字符串转化为分钟
            int m = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
            // 存在相同时间，直接返回 0
            if (bo[m]) return 0;
            bo[m] = true;
        }
        return find(bo);
    }

    public static int find(boolean[] bo) {
        int min = 1440;
        int minIndex = 1440;
        int pre = -1;
        for (int i = 0; i < bo.length; i++) {
            if (bo[i]) {
                if (pre >= 0) {
                    min = Math.min(min, i - pre);
                }
                pre = i;
                minIndex = Math.min(minIndex, i);
            }
        }
        if (minIndex != 1440) {
            min = Math.min(min, minIndex + 1440 - pre);
        }
        return min;
    }
}
