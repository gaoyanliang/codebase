package com.yanliang.algo.offer.tree;

/**
 * 剑指 Offer II 047. 二叉树剪枝 https://leetcode-cn.com/problems/pOCWxh/
 *
 * <p>给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 *
 * <p>节点 node 的子树为 node 本身，以及所有 node 的后代。
 *
 * <p>示例 1:
 *
 * <p>输入: [1,null,0,0,1] 输出: [1,null,0,null,1] 解释: 只有红色节点满足条件“所有不包含 1 的子树”。 右图为返回的答案。
 *
 * <p>示例 2:
 *
 * <p>输入: [1,0,1,0,0,0,1] 输出: [1,null,1,null,1] 解释:
 *
 * <p>示例 3:
 *
 * <p>输入: [1,1,0,1,1,0,1,0] 输出: [1,1,0,1,1,null,1] 解释:
 *
 * <p>提示:
 *
 * <p>二叉树的节点个数的范围是 [1,200] 二叉树节点的值只会是 0 或 1
 *
 * @author yanliang
 */
public class PruneTree {

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;
        // 剪枝树的左侧
        root.left = pruneTree(root.left);
        // 剪枝树的右侧
        root.right = pruneTree(root.right);
        // 剪枝自身
        if (root.val == 0 && root.left == null && root.right == null) root = null;
        return root;
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
