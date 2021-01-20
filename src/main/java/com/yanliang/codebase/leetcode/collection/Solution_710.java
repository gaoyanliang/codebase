package com.yanliang.codebase.leetcode.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 给定一个包含 [0，n ) 中独特的整数的黑名单 B，写一个函数从 [ 0，n ) 中返回一个不在 B 中的随机整数。
 *
 * 对它进行优化使其尽量少调用系统方法 Math.random() 。
 *
 *分析
 *
 * 白名单中数的个数为 N - len(B)，那么可以直接在 [0, N - len(B)) 中随机生成整数。我们把所有小于 N - len(B) 且在黑名单中数一一映射到大于等于 N - len(B) 且出现在白名单中的数。这样一来，如果随机生成的整数出现在黑名单中，我们就返回它唯一对应的那个出现在白名单中的数即可。
 *
 * 例如当 N = 6，B = [0, 2, 3] 时，我们在 [0, 3) 中随机生成整数，并将 2 映射到 4，3 映射到 5，这样随机生成的整数就是 [0, 1, 4, 5] 中的一个。
 *
 * 算法
 *
 * 我们将黑名单分成两部分，第一部分 X 的数都小于 N - len(B)，需要进行映射；第二部分 Y 的数都大于等于 N - len(B)，这些数不需要进行映射，因为并不会随机到它们。
 *
 * 我们先用 Y 构造出 W，表示大于等于 N - len(B) 且在白名单中的数，X 和 W 的长度一定是相等的。随后遍历 X 和 W，构造一个映射表（HashMap）M，将 X 和 W 中的数构造一一映射。
 *
 * 在 [0, N - len(B)) 中随机生成整数 a 时，如果 a 出现在 M 中，则将它的映射返回，否则直接返回 a。
 *
 *
 * @author yanliang
 * @date 10/11/2020 9:38 AM
 */
public class Solution_710 {

	int write_len;
	Random random;
	Map<Integer, Integer> map;

	public Solution_710(int N, int[] blacklist) {
		map = new HashMap<>();
		random = new Random();
		write_len = N - blacklist.length;

		// 大于等于 N - len(B) 且出现在白名单中的数
		Set<Integer> write = new HashSet<>();
		for (int i = N - blacklist.length; i < N; i ++) {
			write.add(i);
		}
		for (int b : blacklist) {
			write.remove(b);
		}

		// 把所有小于 N - len(B) 且在黑名单中数一一映射到大于等于 N - len(B) 且出现在白名单中的数。
		Iterator<Integer> iterator = write.iterator();
		for (int b : blacklist) {
			if (b < write_len) {
				map.put(b, iterator.next());
			}
		}
	}

	public int pick() {
		int i = random.nextInt(write_len);
		return map.getOrDefault(i, i);
	}


	public static void main(String[] args) {
		Solution_710 solution_710 = new Solution_710(10, new int[]{1, 3, 5, 7, 9});
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
		System.out.println(solution_710.pick());
	}
}
