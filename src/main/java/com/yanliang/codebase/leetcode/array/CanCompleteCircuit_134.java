package com.yanliang.codebase.leetcode.array;

/**
 * 134. 加油站
 *
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明: 
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * @author yanliang
 * @date 11/18/2020 12:02 PM
 */
public class CanCompleteCircuit_134 {


	public static void main(String[] args) {
		int[] gas  = {1,2,3,4,5};
		int[] cost = {3,4,5,1,2};

		System.out.println(canCompleteCircuit(gas, cost));
	}


	public static int canCompleteCircuit(int[] gas, int[] cost) {
		int i = 0, cnt;
		int n = gas.length;
		while (i < n) {
			cnt = 0;
			int sumGas = 0, sumCost = 0;
			while (cnt < n) {
				int j = (i + cnt) % n;
				sumCost += cost[j];
				sumGas += gas[j];
				if (sumGas < sumCost) {
					break;
				}
				cnt ++;
			}
			if (cnt == n) {
				return i;
			} else {
				i = i + cnt + 1;
			}
		}
		return -1;
	}
}
