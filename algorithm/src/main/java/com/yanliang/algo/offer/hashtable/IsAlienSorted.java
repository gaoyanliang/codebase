package com.yanliang.algo.offer.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 034. 外星语言是否排序 https://leetcode-cn.com/problems/lwyVBB/
 *
 * <p>某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * <p>给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 * <p>示例 1：
 *
 * <p>输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz" 输出：true 解释：在该语言的字母表中，'h'
 * 位于 'l' 之前，所以单词序列是按字典序排列的。 示例 2：
 *
 * <p>输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz" 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。 示例 3：
 *
 * <p>输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz" 输出：false 解释：当前三个字符 "app"
 * 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 *
 * <p>提示：
 *
 * <p>1 <= words.length <= 100 1 <= words[i].length <= 20 order.length == 26 在 words[i] 和 order
 * 中的所有字符都是英文小写字母。
 */
public class IsAlienSorted {

    public static void main(String[] args) {
        String[] str1 = {"hello", "leetcode"};
        String[] str2 = {"word", "world", "row"};
        String[] str3 = {"apple", "app"};
        System.out.println(isAlienSorted(str1, "hlabcdefgijkmnopqrstuvwxyz")); // true
        System.out.println(isAlienSorted(str2, "worldabcefghijkmnpqstuvxyz")); // false
        System.out.println(isAlienSorted(str3, "abcdefghijklmnopqrstuvwxyz")); // false
    }

    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> table = new HashMap<>(26);
        int i = 0;
        for (char ch : order.toCharArray()) {
            table.put(ch, i++);
        }

        for (int j = 0; j < words.length - 1; j++) {
            if (!sortStr(words[j], words[j + 1], table)) return false;
        }

        return true;
    }

    public static boolean sortStr(String s1, String s2, Map<Character, Integer> table) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int i = 0;
        while (i < chs1.length && i < chs2.length) {
            int a = table.get(chs1[i]);
            int b = table.get(chs2[i]);

            if (a < b) return true;
            if (a > b) return false;
            i++;
        }
        // apple app 当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅'
        // 是空白字符，定义为比任何其他字符都小
        return i == chs1.length;
    }
}
