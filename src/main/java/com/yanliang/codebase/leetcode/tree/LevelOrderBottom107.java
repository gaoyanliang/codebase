package com.yanliang.codebase.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yanliang
 */
public class LevelOrderBottom107 {
	public static void main(String[] args) {
		TreeNode left = new TreeNode(9);
		TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
		TreeNode root = new TreeNode(3, left, right);

		System.out.println(levelOrderBottom(root));

	}

	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;

		Stack<List<Integer>> stack  = new Stack<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> tmp = new ArrayList<>();
			while (size > 0) {
				TreeNode node = queue.poll();
				tmp.add(node.val);
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
				size --;
			}
			stack.push(tmp);
		}


		while (!stack.isEmpty()) {
			res.add(stack.pop());
		}
		return res;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}

