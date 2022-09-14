package com.yanliang.algo.binary_tree.lc;

import com.yanliang.algo.binary_tree.TreeNode;

/**
 * 二叉树的最大深度 LC104  https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * @author yanliang
 */
public class MaxDepth {


    static int depth = 0;
    public static int maxDepth(TreeNode root) {
        traverse(root, 0);
        return depth;
    }

    public static void traverse(TreeNode root, int tmp) {
//        if (root == null) return ;
//
//        if (root.left == null && root.right == null) {
//            depth = tmp > depth ? tmp : depth;
//        }
//        traverse(root.left, tmp + 1);
//        traverse(root.right, tmp + 1);
    }
}
