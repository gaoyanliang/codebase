package com.yanliang.algo.offer.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图 https://leetcode-cn.com/problems/WNC0Lk/ 给定一个二叉树的 根节点
 * root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * <p>示例 1:
 *
 * <p>输入: [1,2,3,null,5,null,4] 输出: [1,3,4] 示例 2:
 *
 * <p>输入: [1,null,3] 输出: [1,3] 示例 3:
 *
 * <p>输入: [] 输出: []
 *
 * <p>提示:
 *
 * <p>二叉树的节点个数的范围是 [0,100] -100 <= Node.val <= 100
 *
 * @author yanliang
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int v = queue.peek().val;
            while (size > 0) {
                TreeNode tmp = queue.poll();
                v = tmp.val;
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
                size--;
            }
            res.add(v);
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
