package com.yanliang.algo.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出两个有序列表的交集
 *
 * @author yanliang
 */
public class SortArrayIntersection {

    /**
     * 方法一
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static List<Integer> intersection1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }

    /**
     * 方法二 - 二分
     *
     * @param array1
     * @param array2
     * @return
     */
    public static List<Integer> intersection2(int[] array1, int[] array2) {

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 0; i < array1.length; i++) {
            if (select(array2, array1[i])) {
                results.add(array1[i]);
            }
        }

        return results;
    }

    private static boolean select(int[] a, int num) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == num) {
                return true;
            } else if (a[mid] > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 200};
        int[] b = {3, 6, 9, 200};

        long start = System.currentTimeMillis();
        System.out.println(intersection1(a, b));
        System.out.println("方法一耗时： " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(intersection2(a, b));
        System.out.println("方法二耗时： " + (System.currentTimeMillis() - start));
    }
}
