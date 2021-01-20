package com.yanliang.codebase.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间：给出一个区间的集合，请合并所有重叠的区间。
 *
 *示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。

 * 提示：
 *
 * intervals[i][0] <= intervals[i][1]

 * @author yanliang
 * @date 10/13/2020 9:18 AM
 */
public class Merge_56 {

	public static void main(String[] args) {
		int[][] intvs1 = {{1, 4}, {4, 5}};
		int[][] intvs2 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

		// 期待 【1， 5】
		System.out.println(merge(intvs1));

		// 期待 【1， 6】 【8， 10】 【15， 18】
		System.out.println(merge(intvs2));
	}


	/**
	 * 合并区间
	 * @param intervals
	 * @return
	 */
	public static int[][] merge(int[][] intervals) {
		if (intervals.length == 0) {
			return new int[0][0];
		}

		// 先排序 左升序右降序
		Arrays.sort(intervals, (a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			}
			return a[0] - b[0];
		});

		// 存放合并后的区间
		List<int[]> res = new ArrayList<>();
		int[] terv = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			int[] cur = intervals[i];
			// 相交 更新end
			if (terv[1] < cur[0]) {
				res.add(terv);
				terv = cur;
			} else if (terv[0] <= cur[0] && terv[1] <= cur[1]) {
				terv[1] = cur[1];
			}
		}
		res.add(terv);
		return res.toArray(new int[res.size()][2]);
	}
}
