package com.yanliang.algo.sliding_window;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 187. 重复的DNA序列
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 *
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 *
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 105
 * s[i]=='A'、'C'、'G' or 'T'
 * @author yanliang
 */
public class RepeatedDnaSequencesLC187 {


    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() < 10) return ret;

        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        int right = 0;
        for (; right < 10; right ++) {
            sb.append(s.charAt(right));
        }
        set.add(sb.toString());

        while(right < s.length()) {
            sb.deleteCharAt(0);

            sb.append(s.charAt(right ++));
            if (set.contains(sb.toString())) {
                ret.add(sb.toString());
            } else {
                set.add(sb.toString());
            }
        }
        return ret;
    }


    // 高效实现
    public List<String> findRepeatedDnaSequences_fast(String s) {
        if (s.length() <= 10 || s.length() > 10000) return new ArrayList<>();
        Set<String> set = new HashSet<>(), res = new HashSet<>();
        char[] chs = s.toCharArray();
        for (int i = 0; i <= chs.length - 10; i++) {
            String str = String.valueOf(chs, i, 10);
            if (!set.add(str)) res.add(str);
        }
        return new ArrayList<>(res);
    }
}
