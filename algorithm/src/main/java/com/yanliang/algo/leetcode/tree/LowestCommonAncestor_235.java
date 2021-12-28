package com.yanliang.algo.leetcode.tree;

/**
 * @author yanliang
 * @date 2020/10/2821:51
 */
public class LowestCommonAncestor_235 {

    public static PreorderTraversal.TreeNode lowestCommonAncestor(
            PreorderTraversal.TreeNode root,
            PreorderTraversal.TreeNode p,
            PreorderTraversal.TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
