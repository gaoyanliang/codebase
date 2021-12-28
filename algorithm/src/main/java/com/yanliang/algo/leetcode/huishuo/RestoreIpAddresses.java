package com.yanliang.algo.leetcode.huishuo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * <p>有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * <p>例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是
 * 无效 IP 地址。
 *
 * <p>https://leetcode-cn.com/problems/restore-ip-addresses
 *
 * @author yanliang
 */
public class RestoreIpAddresses {

    static int IP_NUM = 4;
    static int[] ip = new int[IP_NUM];
    static Set<String> ans = new HashSet<>();

    public static void main(String[] args) {
        List<String> list = restoreIpAddresses("25525511135");
        List<String> list1 = restoreIpAddresses("0000");
        List<String> list2 = restoreIpAddresses("1111");
        List<String> list3 = restoreIpAddresses("010010");
        List<String> list4 = restoreIpAddresses("101023");
        System.out.println();
    }

    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len < 4) return new ArrayList<>();
        dfs(s, 0, 0);
        return new ArrayList<>(ans);
    }

    public static void dfs(String s, int start, int ipIndex) {
        if (ipIndex == IP_NUM) {
            if (start == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= 4; i++) {
                    sb.append(ip[i - 1]);
                    if (i != 4) sb.append(".");
                }
                ans.add(sb.toString());
            }
            return;
        }

        if (start == s.length()) return;

        if (s.charAt(start) == '0') {
            ip[ipIndex] = 0;
            dfs(s, start + 1, ipIndex + 1);
        }

        int num = 0;
        for (int i = start; i < s.length(); i++) {
            num = num * 10 + s.charAt(i) - '0';
            if (num > 0 && num <= 255) {
                ip[ipIndex] = num;
                dfs(s, i + 1, ipIndex + 1);
            } else {
                break;
            }
        }
    }
}
