package com.yanliang.algo.offer.array;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author yanliang
 */
public class ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> windows = new TreeSet<>();
        for (int i = 0; i < nums.length; i ++) {
            Long tmp = windows.ceiling((long) t - (long) nums[i]);
            if (tmp != null && tmp <= (long) nums[i] + (long) t) {
                return true;
            }
            windows.add((long) nums[i]);
            if (i >= k) {
                windows.pollFirst();
            }
        }

        return false;
    }
}
