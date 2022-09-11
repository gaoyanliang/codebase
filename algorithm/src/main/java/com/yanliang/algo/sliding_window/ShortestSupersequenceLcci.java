package com.yanliang.algo.sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 面试题 17.18. 最短超串
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 *
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 *
 * 示例 1:
 *
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 * 示例 2:
 *
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 * 提示：
 *
 * big.length <= 100000
 * 1 <= small.length <= 100000
 *
 * [521704, 897261, 279103, 381783, 668374, 934085, 254258, 726184, 496153, 804155]
 * [897261, 934085, 381783, 496153]
 * @author yanliang
 */
public class ShortestSupersequenceLcci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] big = new int[n];
        int[]small = new int[m];

        for (int i = 0; i < n; i ++) {
            big[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i ++) {
            small[i] = sc.nextInt();
        }

        int[] ret = shortestSeq(big, small);
        System.out.println(ret[0] + "  " + ret[1]);
    }



    public static int[] shortestSeq(int[] big, int[] small) {
        Set<Integer> need = new HashSet<>();
        for (int num: small) {
            need.add(num);
        }

        int left = 0, right = 0, valid = 0;
        int[] ret = new int[2];
        Map<Integer, Integer> windows = new HashMap<>();
        while (right < big.length) {
            int key = big[right ++];
            if (need.contains(key)) {
                windows.put(key, windows.getOrDefault(key, 0) + 1);
                if (windows.get(key) == 1) valid ++;
            }

            while(valid == need.size() && right - left >= need.size()) {
                if (right - left < ret[1] + 1 - ret[0] || ret[1] == 0) {
                    ret[0] = left;
                    ret[1] = right - 1;
                }
                key = big[left ++];
                if (need.contains(key)) {
                    if (windows.get(key) == 1) valid --;
                    windows.put(key, windows.get(key) - 1);
                }
            }
        }
        return ret[1] == 0 ? new int[0] : ret;
    }
}
