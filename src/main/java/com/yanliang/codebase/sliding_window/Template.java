package com.yanliang.codebase.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口模板
 * https://labuladong.gitee.io/algo/1/9/
 */
public class Template {

    public void sliding_window(String s, String t){
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        
        for (int i = 0; i < t.length(); i ++) {
            char ch = t.charAt(i);
            need.putIfAbsent(ch, 0);
            need.put(ch, need.get(ch) + 1);
        }
        
        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            // ch 是将要放入窗口的字符
            char ch = s.charAt(right);
            // 右移窗口
            right ++;
            // 进行窗口内数据的一系列操作...

            /*** debug 输出的位置 ***/
            System.out.println("window: [ "+ left + "," + right + " ]");
            /********************/

            // 判断左窗口是否要收缩
            while (true /** window need shrink */) {
                // d 是将要移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left ++;
                // 进行窗口内数据的一系列操作...
            }

        }
        
    }
}
