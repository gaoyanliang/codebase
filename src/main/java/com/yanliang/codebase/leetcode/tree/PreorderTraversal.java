package com.yanliang.codebase.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树 前序遍历
 * @author yanliang
 * @date 10/13/2020 11:13 AM
 */
public class PreorderTraversal {

	public static void main(String[] args) {

	}

	/**
	 * 前序遍历  迭代实现
	 * @param root
	 * @return
	 */
	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Deque<TreeNode> stack = new LinkedList<>();
		stack.addFirst(root);
		while (!stack.isEmpty()) {
			TreeNode treeNode = stack.removeFirst();
			res.add(treeNode.val);
			if (treeNode.right != null) stack.addFirst(treeNode.right);
			if (treeNode.left != null) stack.addFirst(treeNode.left);
		}
		return res;
	}

	/**
	 * 二叉树节点
	 */
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}


