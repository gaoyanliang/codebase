package com.yanliang.codebase.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode 94 二叉树，中序遍历
 * @author yanliang
 * @date 10/13/2020 11:25 AM
 */
public class InorderTraversal {

	public static void main(String[] args) {
		PreorderTraversal.TreeNode root = new PreorderTraversal.TreeNode(1);
		PreorderTraversal.TreeNode right = new PreorderTraversal.TreeNode(2);
		PreorderTraversal.TreeNode left = new PreorderTraversal.TreeNode(3);

		root.right = right;
		root.right.left = left;

		PreorderTraversal.TreeNode tmp = root;

		List<Integer> integers = inorderTraversal(root);
		System.out.println("迭代遍历：");
		for (Integer s : integers) {
			System.out.print(s + " ");
		}
		System.out.println();

		List<Integer> integers1 = inorderTraversal1(tmp);
		System.out.println("递归遍历：");
		for (Integer s : integers1) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	/**
	 * 中序遍历，迭代实现
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversal(PreorderTraversal.TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Stack<PreorderTraversal.TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || root != null) {
			// 如果左子树不为空，先访问左子树
			if (root != null) {
				stack.push(root);
				root = root.left;
				continue;
			}

			root = stack.pop();
			// 访问根节点
			res.add(root.val);
			// 访问右子树
			root = root.right;
		}
		return res;
	}

	/**
	 * 中序遍历：递归实现
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversal1(PreorderTraversal.TreeNode root) {
		List<Integer> res = new ArrayList<>();
		inorderTraversal(root, res);
		return res;
	}

	public static void inorderTraversal(PreorderTraversal.TreeNode root, List<Integer> res) {
		if (root == null) {
			return ;
		}
		inorderTraversal(root.left, res);
		res.add(root.val);
		inorderTraversal(root.right, res);

	}


}