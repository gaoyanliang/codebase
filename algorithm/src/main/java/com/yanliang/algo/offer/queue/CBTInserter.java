package com.yanliang.algo.offer.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 043. 往完全二叉树添加节点 https://leetcode-cn.com/problems/NaqhDT/
 *
 * <p>完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。
 *
 * <p>设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * <p>CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构； CBTInserter.insert(int v)
 * 向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值； CBTInserter.get_root() 将返回树的根节点。
 *
 * <p>示例 1：
 *
 * <p>输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]] 输出：[null,1,[1,2]] 示例
 * 2：
 *
 * <p>输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs =
 * [[[1,2,3,4,5,6]],[7],[8],[]] 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *
 * <p>提示：
 *
 * <p>最初给定的树是完全二叉树，且包含 1 到 1000 个节点。 每个测试用例最多调用 CBTInserter.insert 操作 10000 次。 给定节点或插入节点的每个值都在 0 到
 * 5000 之间。
 *
 * @author yanliang
 */
public class CBTInserter {

    /** 根节点 */
    private TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
    }

    public int insert(int v) {
        if (root == null) {
            this.root = new TreeNode(v);
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            res = tmp.val;
            if (tmp.left != null) {
                queue.offer(tmp.left);
            } else {
                tmp.left = new TreeNode(v);
                break;
            }

            if (tmp.right != null) {
                queue.offer(tmp.right);
            } else {
                tmp.right = new TreeNode(v);
                break;
            }
        }
        return res;
    }

    public TreeNode get_root() {
        return root;
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
