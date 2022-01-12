package com.yanliang.algo.offer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 052. 展平二叉搜索树 https://leetcode-cn.com/problems/NYBBNL/ 给你一棵二叉搜索树，请 按中序遍历
 * 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * <p>示例 1：
 *
 * <p>输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9] 示例 2：
 *
 * <p>输入：root = [5,1,7] 输出：[1,null,5,null,7]
 *
 * <p>提示：
 *
 * <p>树中节点数的取值范围是 [1, 100] 0 <= Node.val <= 1000
 *
 * @author yanliang
 */
public class IncreasingBST {

    public TreeNode increasingBST(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);

        TreeNode tmp = new TreeNode(0);
        TreeNode cur = tmp;
        for (int i : nodes) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        return tmp.right;
    }

    public void inorder(TreeNode root, List<Integer> nodes) {
        if (root == null) return;
        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
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
