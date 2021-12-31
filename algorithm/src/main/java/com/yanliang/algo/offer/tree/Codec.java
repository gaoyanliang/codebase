package com.yanliang.algo.offer.tree;

import java.util.LinkedList;

/**
 * 剑指 Offer II 048. 序列化与反序列化二叉树 https://leetcode-cn.com/problems/h54YBf/submissions/
 *
 * <p>序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * <p>请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * <p>示例 1：
 *
 * <p>输入：root = [1,2,3,null,null,4,5] 输出：[1,2,3,null,null,4,5] 示例 2：
 *
 * <p>输入：root = [] 输出：[] 示例 3：
 *
 * <p>输入：root = [1] 输出：[1] 示例 4：
 *
 * <p>输入：root = [1,2] 输出：[1,2]
 *
 * <p>提示：
 *
 * <p>输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，也可以采用其他的方法解决这个问题。 树中结点数在范围 [0,
 * 104] 内 -1000 <= Node.val <= 1000
 *
 * @author yanliang
 */
public class Codec {

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public static void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#");
            sb.append(",");
            return;
        }

        sb.append(root.val);
        sb.append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public static TreeNode deserialize(String data) {
        if (data == null || data.equals("")) return null;
        LinkedList<String> list = new LinkedList<>();
        for (String s : data.split(",")) {
            list.add(s);
        }
        return deserialize(list);
    }

    public static TreeNode deserialize(LinkedList<String> list) {
        if (list.isEmpty()) return null;
        String s = list.removeFirst();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserialize(list);
        root.right = deserialize(list);
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
