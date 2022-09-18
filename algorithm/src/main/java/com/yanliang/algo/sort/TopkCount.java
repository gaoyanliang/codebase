package com.yanliang.algo.sort;

import java.util.PriorityQueue;

/**
 * @author yanliang
 */
public class TopkCount {

    public static void main(String[] args) {
        int[] nums = {9, 3, 5, 6, 8, 1, 3, 6, 4, 7, 5, 1, 5, 1, 2, 35, 7, 5, 2, 1, 55, 33, 88, 99};

        int[] ret = topK(nums, 7);

        for (int num: ret) {
            System.out.println(num);
        }

    }

    public static int[] topK(int[] nums, int k) {
        // 小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int num: nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                // 如果发现数据比堆顶元素大，则加入到小顶堆中
                int value = queue.peek();
                if (num > value) {
                    queue.poll();
                    queue.offer(num);
                }
            }
        }

        int[] ret = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            ret[i ++] = queue.poll();
        }
        return ret;
    }
}
