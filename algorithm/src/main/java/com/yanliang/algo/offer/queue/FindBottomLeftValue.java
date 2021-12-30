package com.yanliang.algo.offer.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值 https://leetcode-cn.com/problems/LwUNpT/
 *
 * <p>给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * <p>假设二叉树中至少有一个节点。
 *
 * <p>示例 1:
 *
 * <p>输入: root = [2,1,3] 输出: 1 示例 2:
 *
 * <p>输入: [1,2,3,4,null,5,6,null,null,7] 输出: 7
 *
 * <p>提示:
 *
 * <p>二叉树的节点个数的范围是 [1,104] -231 <= Node.val <= 231 - 1
 *
 * @author yanliang
 */
public class FindBottomLeftValue {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek().val;
            while (size > 0) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
                size--;
            }
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
