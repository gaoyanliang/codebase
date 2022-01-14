package com.yanliang.algo.offer.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和 https://leetcode-cn.com/problems/opLdQZ/
 *
 * <p>给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
 *
 * <p>示例 1：
 *
 * <p>输入: root = [8,6,10,5,7,9,11], k = 12 输出: true 解释: 节点 5 和节点 7 之和等于 12 示例 2：
 *
 * <p>输入: root = [8,6,10,5,7,9,11], k = 22 输出: false 解释: 不存在两个节点值之和为 22 的节点
 *
 * <p>提示：
 *
 * <p>二叉树的节点个数的范围是 [1, 104]. -104 <= Node.val <= 104 root 为二叉搜索树 -105 <= k <= 105
 *
 * @author yanliang
 */
public class FindTarget {

    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
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
