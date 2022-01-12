package com.yanliang.algo.offer.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 050. 向下的路径节点之和 https://leetcode-cn.com/problems/6eUYwP/
 *
 * <p>给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * <p>路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * <p>示例 1：
 *
 * <p>输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8 输出：3 解释：和等于 8 的路径有 3 条，如图所示。 示例 2：
 *
 * <p>输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22 输出：3
 *
 * <p>提示:
 *
 * <p>二叉树的节点个数的范围是 [0,1000] -109 <= Node.val <= 109 -1000 <= targetSum <= 1000
 *
 * @author yanliang
 */
public class PathSum {

    public int pathSum(TreeNode root, int targetSum) {
        // 记录前缀和
        Map<Long, Integer> prefix = new HashMap<>();
        // 保证不漏掉 cur == target 的情况
        prefix.put(0L, 1);
        return dfs(root, targetSum, 0L, prefix);
    }

    public int dfs(TreeNode root, int targetSum, Long cur, Map<Long, Integer> prefix) {
        if (root == null) return 0;
        cur += root.val;

        int ret = prefix.getOrDefault(cur - targetSum, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        ret += dfs(root.left, targetSum, cur, prefix);
        ret += dfs(root.right, targetSum, cur, prefix);
        prefix.put(cur, prefix.getOrDefault(cur - targetSum, 0) - 1);
        return ret;
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
