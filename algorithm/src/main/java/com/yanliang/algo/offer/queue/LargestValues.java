package com.yanliang.algo.offer.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值 https://leetcode-cn.com/problems/hPov7L/
 *
 * <p>给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * <p>示例1：
 *
 * <p>输入: root = [1,3,2,5,3,null,9] 输出: [1,3,9] 解释: 1 / \ 3 2 / \ \ 5 3 9 示例2：
 *
 * <p>输入: root = [1,2,3] 输出: [1,3] 解释: 1 / \ 2 3 示例3：
 *
 * <p>输入: root = [1] 输出: [1] 示例4：
 *
 * <p>输入: root = [1,null,2] 输出: [1,2] 解释: 1 \ 2 示例5：
 *
 * <p>输入: root = [] 输出: []
 *
 * <p>提示：
 *
 * <p>二叉树的节点个数的范围是 [0,104] -231 <= Node.val <= 231 - 1
 *
 * @author yanliang
 */
public class LargestValues {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int num = queue.size();
            while (num > 0) {
                TreeNode tmp = queue.poll();
                max = Math.max(max, tmp.val);
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
                num--;
            }
            res.add(max);
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
