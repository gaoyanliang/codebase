package com.yanliang.algo.offer.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanliang
 */
public class TopKFrequent {

    public static void main(String[] args) {
        int[] nums1 = {1,1,1,2,2,3};
        int[] nums2 = {1};
        int[] nums3 = {1,2};

        System.out.println(topKFrequent(nums1, 2));
        System.out.println(topKFrequent(nums2, 1));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 只存在一个数字
        if (nums.length == 1) return nums;

        // 查找数组中不同数字的范围
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i: nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        // 数组中的所有数字都相同
        if (max == min) return new int[]{min};

        // 最多存在 max - min + 1 个不同的数字，记录他们的个数
        int[] count = new int[max - min + 1];
        for (int i: nums) {
            count[i - min] ++;
        }

        // 将出现次数相同的数字合并到一起
        List<Integer>[] lists = new List[nums.length];
        int index = 0;
        for (int times: count) {
            if (lists[times] == null) {
                lists[times] = new ArrayList<>();
            }
            lists[times].add(index + min);
            index ++;
        }
        index = 0;
        int[] res = new int[k];
        for (int i = nums.length - 1; k > 0; i --) {
            if (lists[i] != null) {
                for (int num: lists[i]) {
                    if (k > 0) {
                        res[index ++] = num;
                        k --;
                    }
                }
            }
        }
        return res;
     }
}
