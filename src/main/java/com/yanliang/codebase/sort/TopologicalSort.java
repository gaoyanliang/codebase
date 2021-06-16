package com.yanliang.codebase.sort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author yanliang
 */
public class TopologicalSort {

	public static void main(String[] args) {
		int[][] prere1 = {{0,2}, {1, 2}, {1, 2}, {3, 2}, {4, 2}};
		int[][] prere2 = {{0,2}, {1, 2}, {1, 2}, {2, 3}, {3, 4}, {4, 2}};
		System.out.println(topologicalSort(5, prere1));
		System.out.println(topologicalSort(5, prere2));
	}

	public static boolean topologicalSort(int n, int[][] prere) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		int[] in = new int[n];
		for (int[] pre: prere) {
			in[pre[1]] ++;
			Set<Integer> integers = map.getOrDefault(pre[0], new HashSet<>());
			integers.add(pre[1]);
			map.put(pre[0], integers);

		}
		Stack<Integer> stack = new Stack<>();
		int sum = 0;
		for(int i = 0; i < n; i ++) {
			if (in[i] == 0) {
				sum ++;
				stack.push(i);
			}
		}
		while (!stack.isEmpty()) {
			int p = stack.pop();
			Set<Integer> integers = map.getOrDefault(p, new HashSet<>());
			for (int i: integers) {
				in[i] --;
				if (in[i] == 0){
					stack.push(i);
					sum ++;
				}
			}
		}
		return sum == n;
	}


}
