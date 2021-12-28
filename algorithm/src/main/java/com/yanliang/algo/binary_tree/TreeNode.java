package com.yanliang.algo.binary_tree;

/**
 * 二叉树节点
 *
 * @author yanliang
 * @date 8/25/20206:41 PM
 */
public class TreeNode<E extends Comparable<E>> {
    /** 节点数据 */
    E val;
    /** 左子树 */
    TreeNode<E> left;
    /** 右子树 */
    TreeNode<E> right;

    public TreeNode(E val) {
        this.val = val;
    }

    /**
     * 向二叉树内添加节点
     *
     * @param child
     */
    public void addChild(TreeNode<E> child) {
        if (child == null) {
            System.out.println("======> error! 根节点为空，无法添加子节点。");
            return;
        }
        if (this.compareTo(child) >= 0) {
            if (this.left == null) {
                this.left = child;
            } else {
                this.left.addChild(child);
            }
        } else {
            if (this.right == null) {
                this.right = child;
            } else {
                this.right.addChild(child);
            }
        }
    }

    public int compareTo(TreeNode<E> treeNode) {
        return this.val.compareTo(treeNode.val);
    }

    /** 中序遍历打印二叉树 */
    public void printNode() {
        if (this.left != null) {
            this.left.printNode();
        }
        System.out.println(this.val.toString());
        if (this.right != null) {
            this.right.printNode();
        }
    }
}
