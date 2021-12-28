package com.yanliang.algo.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * <p>请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * <p>示例: 你可以将以下二叉树：
 *
 * <p>4 / \ 2 7 / \ 5 6
 *
 * <p>序列化为 "[1,2,3,null,null,4,5]"
 *
 * @author yanliang
 * @date 8/26/20206:41 PM
 */
public class Codec {

    // 代表分隔符的字符
    private static final String SEP = ",";

    // 代表 null 空指针的字符
    private static final String NULL = "#";

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.addElement(4);
        tree.addElement(2);
        tree.addElement(7);
        tree.addElement(5);
        tree.addElement(6);

        // ---------- 序列化 ----------
        // 前序
        StringBuilder pre_sb = new StringBuilder();
        pre_serialize(tree.getRoot(), pre_sb);
        System.out.println("==> 序列化（前序遍历）： " + pre_sb.toString());
        // 后序
        StringBuilder post_sb = new StringBuilder();
        post_serialize(tree.getRoot(), post_sb);
        System.out.println("==> 序列化（后序遍历）： " + post_sb.toString());
        // 层次遍历
        String serialize = serialize(tree.getRoot());
        System.out.println("==> 序列化（层次遍历）： " + post_sb.toString());

        // ---------- 反序列化 ----------
        TreeNode tree1 = pre_deserialize(pre_sb.toString());
        TreeNode tree2 = post_deserialize(post_sb.toString());
        TreeNode deserialize = deserialize(serialize);

        System.out.println("==> 层次遍历前序序列化结果");
        Traversal.levelTraversal(tree1);
        System.out.println("==> 层次遍历后序序列化结果");
        Traversal.levelTraversal(tree2);
        Traversal.levelTraversal(deserialize);
    }

    // ********************************************************
    // ********************* 前序遍历解法 ***********************
    // ********************************************************

    /**
     * 将二叉树序列化为字符串
     *
     * @param root
     * @return
     */
    public static void pre_serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        /** **** 前序遍历位置 ***** */
        sb.append(root.val).append(SEP);
        /** ******************** */
        pre_serialize(root.left, sb);
        pre_serialize(root.right, sb);
    }

    /**
     * 将二叉树序列化为字符串列表
     *
     * @param root
     * @param list
     */
    public static void pre_serialize(TreeNode root, LinkedList<String> list) {
        if (root == null) {
            list.addLast(NULL);
        }

        /** **** 前序遍历位置 ***** */
        list.addLast(String.valueOf(root.val));
        /** ******************** */
        pre_serialize(root.left, list);
        pre_serialize(root.right, list);
    }

    /**
     * 将编码数据解码为树。
     *
     * @param data
     * @return
     */
    public static TreeNode pre_deserialize(String data) {
        if (data == null) {
            return null;
        }
        LinkedList<String> notes = new LinkedList<String>();
        for (String s : data.split(SEP)) {
            notes.addLast(s);
        }
        return pre_deserialize(notes);
    }

    /**
     * 解码（反序列化）
     *
     * @param notes
     * @return
     */
    public static TreeNode pre_deserialize(LinkedList<String> notes) {
        if (notes.isEmpty()) {
            return null;
        }
        String note = notes.removeFirst();
        if (note.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(note));

        root.left = pre_deserialize(notes);
        root.right = pre_deserialize(notes);

        return root;
    }

    // ********************************************************
    // ********************* 后序遍历解法 ***********************
    // ********************************************************

    /**
     * 编码（序列化为字符串）
     *
     * @param root
     * @param sb
     */
    public static void post_serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        post_serialize(root.left, sb);
        post_serialize(root.right, sb);

        /** **** 后序遍历位置 ***** */
        sb.append(root.val).append(SEP);
        /** ******************** */
    }

    /**
     * 编码（序列化为字符串列表）
     *
     * @param root
     * @param list
     */
    public static void post_serialize(TreeNode root, LinkedList<String> list) {
        if (root == null) {
            list.addLast(NULL);
            return;
        }

        post_serialize(root.left, list);
        post_serialize(root.right, list);

        list.addLast(String.valueOf(root.val));
    }

    /**
     * 解码
     *
     * @param data
     * @return
     */
    public static TreeNode post_deserialize(String data) {
        if (data == null) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<String>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return post_deserialize(nodes);
    }

    /**
     * 解码
     *
     * @param nodes
     * @return
     */
    public static TreeNode post_deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String node = nodes.removeLast();
        if (node.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(node));

        root.right = post_deserialize(nodes);
        root.left = post_deserialize(nodes);

        return root;
    }

    // ********************************************************
    // ********************* 层次遍历解法 ***********************
    // ********************************************************

    /**
     * 将二叉树序列化为字符串
     *
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        // 初始化队列，将 root 加入队列
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            /* 层级遍历代码位置 */
            if (cur == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.val).append(SEP);
            /** ************** */
            q.offer(cur.left);
            q.offer(cur.right);
        }

        return sb.toString();
    }

    /**
     * 将字符串反序列化为二叉树结构
     *
     * @param data
     * @return
     */
    public static TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] nodes = data.split(SEP);
        // 第一个元素就是 root 的值
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        // 队列 q 记录父节点，将 root 加入队列
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);

        for (int i = 1; i < nodes.length; ) {
            // 队列中存的都是父节点
            TreeNode parent = q.poll();
            // 父节点对应的左侧子节点的值
            String left = nodes[i++];
            if (!left.equals(NULL)) {
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            } else {
                parent.left = null;
            }
            // 父节点对应的右侧子节点的值
            String right = nodes[i++];
            if (!right.equals(NULL)) {
                parent.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            } else {
                parent.right = null;
            }
        }
        return root;
    }
}
