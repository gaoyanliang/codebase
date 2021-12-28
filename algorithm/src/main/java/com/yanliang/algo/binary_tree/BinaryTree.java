package com.yanliang.algo.binary_tree;

/**
 * @author yanliang
 * @date 8/25/20206:37 PM
 */
public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * 向二叉树内添加节点
     *
     * @param element
     */
    public void addElement(T element) {
        if (element == null) {
            return;
        }
        TreeNode<T> treeNode = new TreeNode<T>(element);
        if (this.root == null) {
            this.root = treeNode;
        } else {
            this.root.addChild(treeNode);
        }
    }

    /** 打印二叉树 */
    public void printBinaryTree() {
        if (this.root == null) {
            System.out.println("当前为空对象，无法打印。");
            return;
        }
        this.root.printNode();
    }
}
