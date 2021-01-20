package com.yanliang.codebase.leetcode.array;

/**
 * @author yanliang
 * @date 2020/11/323:16
 */
public class ValidMountainArray_941 {


    /**
     * [2,1]
     * [3,5,5]
     * [0,3,2,1]
     * [0,2,3,4,5,2,1,0]
     * [0,2,3,3,5,2,1,0]
     * false
     * false
     * true
     * true
     * false
     * @param args
     */
    public static void main(String[] args) {
        int[] A = {9,8,1};
        System.out.println(validMountainArray(A));
    }

    public static boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }

        int len = A.length, i = 0;
        while (i + 1 < len && A[i] < A[i + 1]) {
            i ++;
        }

        if (i == 0 || i == len - 1) {
            return false;
        }

        while (i + 1 < len && A[i] > A[i + 1]) {
            i --;
        }

        return i == len - 1;
    }
}
