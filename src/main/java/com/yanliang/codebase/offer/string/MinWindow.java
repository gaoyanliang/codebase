package com.yanliang.codebase.offer.string;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("a", "aa"));
    }

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();

        for (int i = 0; i < t.length(); i ++) {
            char ch = t.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int l = 0, r = 0;
        int start = -1, len = -1;
        int valid = need.size();
        while (r < s.length()) {
            char in = s.charAt(r ++);
            if (need.containsKey(in)) {
                windows.put(in, windows.getOrDefault(in, 0) + 1);
                if (windows.get(in).equals(need.get(in))) valid --;
            }

            while (valid == 0) {
                if (start == -1 || r - l < len) {
                    start = l;
                    len = r - l;
                }

                char out = s.charAt(l ++);
                if (windows.containsKey(out)) {
                    windows.put(out, windows.get(out) - 1);
                    if (windows.get(out) < need.get(out)) valid ++;
                }
            }
        }
        return start == -1 ? "" : s.substring(start, start + len);
    }
}
