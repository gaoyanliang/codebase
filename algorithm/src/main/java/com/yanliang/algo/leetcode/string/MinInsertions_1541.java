package com.yanliang.algo.leetcode.string;

/**
 * 1541. 平衡括号字符串的最少插入次数
 * https://leetcode-cn.com/problems/minimum-insertions-to-balance-a-parentheses-string/submissions/
 *
 * <p>https://labuladong.gitee.io/algo/4/32/139/
 *
 * <p>给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
 *
 * <p>任何左括号 '(' 必须对应两个连续的右括号 '))' 。 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。 比方说 "())"， "())(())))" 和
 * "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
 *
 * <p>你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 *
 * <p>请你返回让 s 平衡的最少插入次数。
 *
 * <p>示例 1：
 *
 * <p>输入：s = "(()))" 输出：1 解释：第二个左括号有与之匹配的两个右括号，但是第一个左括号只有一个右括号。我们需要在字符串结尾额外增加一个 ')' 使字符串变成平衡字符串
 * "(())))" 。 示例 2：
 *
 * <p>输入：s = "())" 输出：0 解释：字符串已经平衡了。 示例 3：
 *
 * <p>输入：s = "))())(" 输出：3 解释：添加 '(' 去匹配最开头的 '))' ，然后添加 '))' 去匹配最后一个 '(' 。 示例 4：
 *
 * <p>输入：s = "((((((" 输出：12 解释：添加 12 个 ')' 得到平衡字符串。 示例 5：
 *
 * <p>输入：s = ")))))))" 输出：5 解释：在字符串开头添加 4 个 '(' 并在结尾添加 1 个 ')' ，字符串变成平衡字符串 "(((())))))))" 。
 *
 * <p>提示：
 *
 * <p>1 <= s.length <= 10^5 s 只包含 '(' 和 ')' 。
 *
 * @author yanliang
 */
public class MinInsertions_1541 {

    public int minInsertions(String s) {
        int res = 0, need = 0;
        char[] ch = s.toCharArray();
        int n = ch.length;

        for (int i = 0; i < n; i++) {
            if (ch[i] == '(') {
                need += 2;
                // ()(
                if (need % 2 == 1) {
                    res++;
                    need--;
                }
            } else {
                need--;
                if (need == -1) {
                    res++;
                    need = 1;
                }
            }
        }
        return res + need;
    }
}
