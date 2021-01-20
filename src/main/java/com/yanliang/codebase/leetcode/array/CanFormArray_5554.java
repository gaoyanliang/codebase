package com.yanliang.codebase.leetcode.array;

/**
 * @author yanliang
 * @date 2020/11/121:32
 */
public class CanFormArray_5554 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[][] pieces = {{5}, {4}, {3}, {2}, {1}};

        System.out.println(canFormArray(arr, pieces));
    }


    public static boolean canFormArray(int[] arr, int[][] pieces) {
        for(int j=0;j<pieces.length;j++){
            int index=0;//
            int len=pieces[j].length;//记录第j个数组的长度
            for(int k=0;k<arr.length;k++){//在arr中寻找第j个数组
                if(arr[k]==pieces[j][index]){//找到之后  就开始遍历，两个数组的下标同时相加
                    index++;
                    if(index==len)  break;//如果pieces第j个数组遍历 完了 直接结束 本次循环
                }

            }
            if(index!=len) return false;//判断一下  是否  第j个数组遍历完了 ，如果没有 说明错误

        }
        return true;
    }
}
