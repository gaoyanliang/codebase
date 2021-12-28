package com.yanliang.algo.leetcode.tree;

/**
 * @author yanliang
 * @date 2020/10/2821:51
 */
public class LowestCommonAncestor_236 {

    public static PreorderTraversal.TreeNode lowestCommonAncestor(
            PreorderTraversal.TreeNode root,
            PreorderTraversal.TreeNode p,
            PreorderTraversal.TreeNode q) {
        if (root == null || root == q || root == p) {
            return root;
        }
        PreorderTraversal.TreeNode left = lowestCommonAncestor(root.left, p, q);
        PreorderTraversal.TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
