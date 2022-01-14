package com.yanliang.algo.offer.tree;

/**
 * 剑指 Offer II 062. 实现前缀树 https://leetcode-cn.com/problems/QC3q1f/
 *
 * <p>Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * <p>请你实现 Trie 类：
 *
 * <p>Trie() 初始化前缀树对象。 void insert(String word) 向前缀树中插入字符串 word 。 boolean search(String word) 如果字符串
 * word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。 boolean startsWith(String prefix) 如果之前已经插入的字符串 word
 * 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 * <p>示例：
 *
 * <p>输入 inputs = ["Trie", "insert", "search", "search", "startsWith", "insert", "search"] inputs =
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]] 输出 [null, null, true, false, true,
 * null, true]
 *
 * <p>解释 Trie trie = new Trie(); trie.insert("apple"); trie.search("apple"); // 返回 True
 * trie.search("app"); // 返回 False trie.startsWith("app"); // 返回 True trie.insert("app");
 * trie.search("app"); // 返回 True
 *
 * <p>提示：
 *
 * <p>1 <= word.length, prefix.length <= 2000 word 和 prefix 仅由小写英文字母组成 insert、search 和 startsWith
 * 调用次数 总计 不超过 3 * 104 次
 *
 * @author yanliang
 */
public class Trie {

    Trie[] tries;
    boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        tries = new Trie[26];
        isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (char ch : word.toCharArray()) {
            if (node.tries[ch - 'a'] == null) {
                node.tries[ch - 'a'] = new Trie();
            }
            node = node.tries[ch - 'a'];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for (char ch : word.toCharArray()) {
            if (node.tries[ch - 'a'] == null) {
                return false;
            }
            node = node.tries[ch - 'a'];
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (char ch : prefix.toCharArray()) {
            if (node.tries[ch - 'a'] == null) {
                return false;
            }
            node = node.tries[ch - 'a'];
        }
        return true;
    }
}
