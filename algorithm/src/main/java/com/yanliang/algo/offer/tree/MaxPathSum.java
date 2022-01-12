package com.yanliang.algo.offer.tree;

/**
 * 剑指 Offer II 051. 节点之和最大的路径 https://leetcode-cn.com/problems/jC7MId/
 *
 * <p>路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * <p>路径和 是路径中各节点值的总和。
 *
 * <p>给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 *
 * <p>示例 1：
 *
 * <p>输入：root = [1,2,3] 输出：6 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 示例 2：
 *
 * <p>输入：root = [-10,9,20,null,null,15,7] 输出：42 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * <p>提示：
 *
 * <p>树中节点数目范围是 [1, 3 * 104] -1000 <= Node.val <= 1000
 *
 * @author yanliang
 */
public class MaxPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return max;
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }

    class TreeNode {
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
