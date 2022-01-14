package com.yanliang.algo.offer.tree;

/**
 * 剑指 Offer II 064. 神奇的字典 https://leetcode-cn.com/problems/US1pGT/ 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词
 * 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。
 *
 * <p>实现 MagicDictionary 类：
 *
 * <p>MagicDictionary() 初始化对象 void buildDict(String[] dictionary) 使用字符串数组 dictionary
 * 设定该数据结构，dictionary 中的字符串互不相同 bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个
 * 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *
 * <p>示例：
 *
 * <p>输入 inputs = ["MagicDictionary", "buildDict", "search", "search", "search", "search"] inputs =
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]] 输出 [null, null,
 * false, true, false, false]
 *
 * <p>解释 MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]); magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False magicDictionary.search("leetcoded"); // 返回 False
 *
 * <p>提示：
 *
 * <p>1 <= dictionary.length <= 100 1 <= dictionary[i].length <= 100 dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同 1 <= searchWord.length <= 100 searchWord 仅由小写英文字母组成 buildDict 仅在 search
 * 之前调用一次 最多调用 100 次 search
 *
 * @author yanliang
 */
public class MagicDictionary {

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[] {"hello", "leetcode"});
        System.out.println(magicDictionary.search("hhllo"));
        System.out.println(magicDictionary.search("hell"));
    }

    Trie trie;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        trie = new Trie();
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            trie.insert(s);
        }
    }

    public boolean search(String searchWord) {
        return trie.search(searchWord);
    }

    class Trie {

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
            return dfs(trie, word, 0, 0);
        }

        public boolean dfs(Trie node, String word, int index, int diff) {
            if (diff > 1 || node == null) return false;
            if (index == word.length()) return diff == 1 && node.isEnd;
            for (int i = 0; i < 26; i++) {
                boolean f =
                        dfs(
                                node.tries[i],
                                word,
                                index + 1,
                                i == word.charAt(index) - 'a' ? diff : diff + 1);
                if (f) return f;
            }
            return false;
        }
    }
}
