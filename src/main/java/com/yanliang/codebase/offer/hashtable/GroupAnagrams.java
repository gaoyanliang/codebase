package com.yanliang.codebase.offer.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 剑指 Offer II 033. 变位词组    https://leetcode-cn.com/problems/sfvd7V/
 *
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 *
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};
        System.out.println(groupAnagrams(strs1));
        System.out.println(groupAnagrams(strs2));
        System.out.println(groupAnagrams(strs3));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, LinkedList<String>> table = new HashMap<>();
        String tmp = "";
        // 将字符串拆成字符数组进行排序后，重组为字符串
        for (String str: strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            tmp = new String(chs);
            LinkedList<String> list = table.getOrDefault(tmp, new LinkedList<String>());
            list.add(str);
            table.putIfAbsent(tmp, list);
        }
        if (!table.isEmpty()) {
            table.forEach((s, list) -> {
               res.add(list);
            });
        }
        return res;
    }
}
