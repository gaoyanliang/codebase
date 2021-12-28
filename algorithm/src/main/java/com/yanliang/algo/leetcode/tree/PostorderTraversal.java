package com.yanliang.algo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树：后序遍历
 *
 * @author yanliang
 * @date 10/14/2020 9:49 AM
 */
public class PostorderTraversal {

    public static void main(String[] args) {
        PreorderTraversal.TreeNode root = new PreorderTraversal.TreeNode(1);
        PreorderTraversal.TreeNode right = new PreorderTraversal.TreeNode(2);
        PreorderTraversal.TreeNode left = new PreorderTraversal.TreeNode(3);

        root.right = right;
        root.right.left = left;

        PreorderTraversal.TreeNode tmp = root;

        List<Integer> integers = postorderTraversal1(root);
        System.out.println("迭代遍历：");
        for (Integer s : integers) {
            System.out.print(s + " ");
        }
        System.out.println();

        List<Integer> integers1 = postorderTraversal(tmp);
        System.out.println("递归遍历：");
        for (Integer s : integers1) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static List<Integer> postorderTraversal1(PreorderTraversal.TreeNode root) {
        Stack<PreorderTraversal.TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        PreorderTraversal.TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            // 如果左子树不为空，先访问左子树
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 后序遍历：递归实现
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(PreorderTraversal.TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversal(root, res);
        return res;
    }

    public static void postorderTraversal(PreorderTraversal.TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, res);
        postorderTraversal(root.right, res);
        res.add(root.val);
    }
}
