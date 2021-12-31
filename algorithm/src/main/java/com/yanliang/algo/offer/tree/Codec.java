package com.yanliang.algo.offer.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 错误代码，待修复 剑指 Offer II 048. 序列化与反序列化二叉树  https://leetcode-cn.com/problems/h54YBf/submissions/
 * @author yanliang
 */
public class Codec {


    public static String serialize(TreeNode root) {
        if (root == null) return null;

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        sb.append(root.val);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            if (tmp.left == null) {
                sb.append("#");
            } else {
                sb.append(tmp.left.val);
                queue.offer(tmp.left);
            }

            if (tmp.right == null) {
                sb.append("#");
            } else {
                sb.append(tmp.right.val);
                queue.offer(tmp.right);
            }
        }

        return sb.toString();
    }

    public static TreeNode deserialize(String data) {
        if (data == null || data.equals("")) return null;
        int index = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(data.charAt(index) - '0');
        queue.offer(root);
        while (index < data.length() - 2) {

            if (data.charAt(index + 1) == '#' && data.charAt(index + 2) == '#') {
                index = index + 2;
                queue.poll();
                continue;
            }
            TreeNode tmp = queue.poll();
            index ++;
            if (data.charAt(index) != '#') {
                TreeNode left = new TreeNode(data.charAt(index) - '0');
                tmp.left = left;
                queue.offer(left);
            }
            index ++;
            if (data.charAt(index) != '#') {
                TreeNode right = new TreeNode(data.charAt(index) - '0');
                tmp.right = right;
                queue.offer(right);
            }
        }
        return root;
    }


    public static class TreeNode {
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
