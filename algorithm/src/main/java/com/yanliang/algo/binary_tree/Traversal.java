package com.yanliang.algo.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树遍历 - 前序遍历 - 中序遍历 - 后序遍历 - 层次遍历
 *
 * @author yanliang
 * @date 8/25/20206:11 PM
 */
public class Traversal {

    public static void main(String[] args) {
        // 构造二叉树
        BinaryTree<Integer> bt = new BinaryTree<Integer>();
        bt.addElement(9);
        bt.addElement(3);
        bt.addElement(20);
        bt.addElement(15);
        bt.addElement(7);
        bt.addElement(5);
        bt.printBinaryTree();

        System.out.println("======> 根节点： " + bt.getRoot().val);

        // 层次遍历
        levelTraversal(bt.getRoot());

        int[][] convert = convert(bt.getRoot());
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                System.out.print("======> 前序遍历 ");
            } else if (i == 1) {
                System.out.print("======> 中序遍历：");
            } else {
                System.out.print("======> 后序遍历：");
            }
            for (int j = 0; j < convert[i].length; j++) {
                System.out.print(convert[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 将指定二叉树转换为 前中后序遍历结果
     *
     * @param root
     * @return
     */
    public static int[][] convert(TreeNode root) {
        // 遍历结果
        int[][] result = new int[3][];
        List<Integer> preList = new ArrayList<Integer>();
        List<Integer> inList = new ArrayList<Integer>();
        List<Integer> postList = new ArrayList<Integer>();

        // 前序遍历结果
        preOrderTraversal(root, preList);
        int[] preArray = new int[preList.size()];
        for (int i = 0; i < preArray.length; i++) {
            preArray[i] = preList.get(i);
        }
        result[0] = preArray;

        // 中序遍历结果
        inOrderTraversal(root, inList);
        int[] inArray = new int[inList.size()];
        for (int i = 0; i < inArray.length; i++) {
            inArray[i] = inList.get(i);
        }
        result[1] = inArray;

        // 后序遍历结果
        postOrderTraversal(root, postList);
        int[] postArray = new int[postList.size()];
        for (int i = 0; i < postArray.length; i++) {
            postArray[i] = postList.get(i);
        }
        result[2] = postArray;

        return result;
    }

    /**
     * 前序遍历
     *
     * @param root
     * @param list
     */
    public static void preOrderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add((Integer) root.val);
            preOrderTraversal(root.left, list);
            preOrderTraversal(root.right, list);
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     * @param list
     */
    public static void inOrderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrderTraversal(root.left, list);
            list.add((Integer) root.val);
            inOrderTraversal(root.right, list);
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     * @param list
     */
    public static void postOrderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrderTraversal(root.left, list);
            inOrderTraversal(root.right, list);
            list.add((Integer) root.val);
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public static void levelTraversal(TreeNode root) {
        if (root == null) {
            System.out.println("error: 待遍历的二叉树为空");
        }
        // 将根节点加入队列
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        System.out.println("======> 层次遍历结果如下：");
        System.out.println(root.val);
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    System.out.print(node.left.val + " ");
                }
                if (node.right != null) {
                    queue.add(node.right);
                    System.out.print(node.right.val + " ");
                }
                count--;
            }
            System.out.println();
        }
    }
}
