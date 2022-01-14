package com.yanliang.algo.offer.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/** @author yanliang */
public class KSmallestPairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> heap =
                new PriorityQueue<>(
                        (p1, p2) -> {
                            return p2.get(0) + p2.get(1) - p1.get(0) - p1.get(1);
                        });

        int l1 = Math.min(k, nums1.length);
        int l2 = Math.min(k, nums2.length);

        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                heap.offer(list);

                if (heap.size() > k) heap.poll();
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(heap.poll());
        }

        return res;
    }
}
