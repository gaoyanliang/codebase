package com.yanliang.algo.sort;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 现有n个编译项，编号为0 ~ n-1。给定一个二维数组，表示编译项之间有依赖关系。如[0, 1]表示1依赖于0。
 *
 * <p>若存在循环依赖则返回空；不存在依赖则返回可行的编译顺序。
 *
 * @author yanliang
 */
public class TopologicalSort {

    public static void main(String[] args) {
        int[][] prere1 = {{0, 2}, {1, 2}, {2, 3}, {2, 4}};
        int[][] prere2 = {{0, 2}, {1, 2}, {2, 3}, {3, 4}, {4, 2}};
        System.out.println(topologicalSort2(5, prere1));
        System.out.println(topologicalSort2(5, prere2));
    }

    public static boolean topologicalSort(int n, int[][] prere) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int[] in = new int[n];
        for (int[] pre : prere) {
            in[pre[1]]++;
            Set<Integer> integers = map.getOrDefault(pre[0], new HashSet<>());
            integers.add(pre[1]);
            map.put(pre[0], integers);
        }
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                sum++;
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int p = stack.pop();
            Set<Integer> integers = map.getOrDefault(p, new HashSet<>());
            for (int i : integers) {
                in[i]--;
                if (in[i] == 0) {
                    stack.push(i);
                    sum++;
                }
            }
        }
        return sum == n;
    }

    // -------------------- 二刷
    public static boolean topologicalSort2(int n, int[][] prere) {
        Map<Integer, Set<Integer>> dependent = new HashMap<>();
        int[] inDegree = new int[n];
        int[] out = new int[n];
        int index = 0;

        // 统计入度, 例如（0, 1） 1 的入度 + 1
        for (int i = 0; i < prere.length; i++) {
            inDegree[prere[i][1]]++;
            dependent.putIfAbsent(prere[i][0], new HashSet<>());
            dependent.get(prere[i][0]).add(prere[i][1]);
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) deque.push(i);
        }

        while (!deque.isEmpty()) {
            int o = deque.poll();
            // 维护一个可行的执行顺序
            out[index++] = o;

            Set<Integer> set = dependent.getOrDefault(o, new HashSet<>());
            set.forEach(
                    s -> {
                        inDegree[s]--;
                        if (inDegree[s] == 0) deque.push(s);
                    });
        }

        for (int i = 0; i < n; i++) System.out.print(out[i] + "  ");
        System.out.println();
        return index == n;
    }
}
