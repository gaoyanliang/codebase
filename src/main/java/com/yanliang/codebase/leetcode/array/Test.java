package com.yanliang.codebase.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanliang
 * @date 2020/10/3122:38
 */
public class Test {


    public static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = data.getOrDefault(nums[i], 0);
            data.put(nums[i], sum+1);
        }

        List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<Map.Entry<Integer, Integer>>(data.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Integer>>() {
               public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                   if (o2.getValue() == o1.getValue()) {
                       return o2.getKey() - o1.getKey();
                   }
                   return o1.getValue() - o2.getValue();
               }
         });

        int[] ans = new int[nums.length];
        AtomicInteger index = new AtomicInteger(0);
        infoIds.forEach(entity -> {
            int len = entity.getValue();
            while (len > 0) {
                ans[index.getAndAdd(1)] = entity.getKey();
                len --;
            }
        });
        return ans;
    }






    public static void main(String[] args) {

        int[] data = {-1,1,-6,4,5,-6,1,4,1};
        System.out.println(frequencySort(data));



//        int[][] data = {{3,1}, {9,0}, {1,0}, {1,4}, {5,3}, {8,8}};
//
//        System.out.println(maxWidthOfVerticalArea(data));
//
//        int[][] data1 = {{8,7}, {9,9}, {7,4}, {9,7}};
//
//        System.out.println(maxWidthOfVerticalArea(data1));
    }

    public static int maxWidthOfVerticalArea(int[][] points) {
        List<Integer> data = new ArrayList<>();
        int len =  points.length;
        for (int i = 0; i < len; i ++) {
            int[] point = points[i];
            data.add(point[0]);
        }
        Collections.sort(data);

        int max = 0;
        for (int i = 1; i < data.size(); i ++) {
            max = Math.max(data.get(i) - data.get(i-1), max);
        }
        return max;
    }
}
