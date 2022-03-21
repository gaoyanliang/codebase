package com.yanliang.algo.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** @author yanliang */
public class MinWindow {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            String t = scanner.nextLine();
            System.out.println(minWindow(s, t));
        }
    }

    public static String minWindow(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();
        String res = "";
        if (s_len < t_len) return res;
        int left = 0, right = 0;
        Map<Character, Integer> chs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (Character ch : t.toCharArray()) {
            chs.put(ch, chs.getOrDefault(ch, 0) + 1);
        }
        int need = chs.size();

        int res_len = Integer.MAX_VALUE;
        while (left <= right && right < s_len) {
            char ch = s.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);

            if (chs.containsKey(ch) && chs.get(ch).equals(window.get(ch))) {
                need--;
            }

            if (need == 0) {
                if (right - left + 1 < res_len) {
                    res = s.substring(left, right + 1);
                    res_len = right - left + 1;
                }
            }

            while (need == 0) {
                char ch1 = s.charAt(left++);
                window.put(ch1, window.get(ch1) - 1);
                if (chs.containsKey(ch1) && chs.get(ch1) > window.get(ch1)) {
                    need++;
                } else {
                    if (right - left + 1 < res_len) {
                        res = s.substring(left, right + 1);
                        res_len = right - left + 1;
                    }
                }
            }
            right++;
        }
        return res;
    }
}
