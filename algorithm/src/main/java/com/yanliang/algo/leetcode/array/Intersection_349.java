package com.yanliang.algo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yanliang
 * @date 11/2/2020 3:20 PM
 */
public class Intersection_349 {

    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 4, 5};
        int[] num2 = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(intersection1(num1, num2));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                ans.add(i);
                set.remove(i);
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int index = 0;
        int index1 = 0, len1 = nums1.length;
        int index2 = 0, len2 = nums2.length;

        int[] ans = new int[len1 + len2];

        while (index1 < len1 && index2 < len2) {
            if (nums1[index1] == nums2[index2]) {
                if (index1 == 0 || nums1[index1] != nums1[index1 - 1]) {
                    ans[index++] = nums1[index1];
                }
                index1++;
                index2++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                index1++;
            }
        }
        return Arrays.copyOfRange(ans, 0, index);
    }
}
