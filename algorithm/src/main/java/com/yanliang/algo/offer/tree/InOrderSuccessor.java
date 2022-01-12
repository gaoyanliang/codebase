package com.yanliang.algo.offer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继 https://leetcode-cn.com/problems/P5rCT8/
 *
 * <p>给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * <p>节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 *
 * <p>示例 1：
 *
 * <p>输入：root = [2,1,3], p = 1 输出：2 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。 示例 2：
 *
 * <p>输入：root = [5,3,6,2,4,null,null,1], p = 6 输出：null 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 *
 * <p>提示：
 *
 * <p>树中节点的数目在范围 [1, 104] 内。 -105 <= Node.val <= 105 树中各节点的值均保证唯一。
 *
 * @author yanliang
 */
public class InOrderSuccessor {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).val == p.val && i + 1 < list.size()) {
                return list.get(i + 1);
            }
        }
        return null;
    }

    public void inorder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
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
