package main

import (
	"golang.org/x/exp/slices"
)

// https://leetcode.cn/problems/maximum-elegance-of-a-k-length-subsequence/solutions/2375128/fan-hui-tan-xin-pythonjavacgo-by-endless-v2w1/

//2813. 子序列最大优雅度
//困难
//
//给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
//
//items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
//
//现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories2 计算，
//其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
//
//你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
//
//用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大优雅度。
//
//注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。
//
//
//
//示例 1：
//
//输入：items = [[3,2],[5,1],[10,1]], k = 2
//输出：17
//解释：
//在这个例子中，我们需要选出长度为 2 的子序列。
//其中一种方案是 items[0] = [3,2] 和 items[2] = [10,1] 。
//子序列的总利润为 3 + 10 = 13 ，子序列包含 2 种不同类别 [2,1] 。
//因此，优雅度为 13 + 22 = 17 ，可以证明 17 是可以获得的最大优雅度。
//示例 2：
//
//输入：items = [[3,1],[3,1],[2,2],[5,3]], k = 3
//输出：19
//解释：
//在这个例子中，我们需要选出长度为 3 的子序列。
//其中一种方案是 items[0] = [3,1] ，items[2] = [2,2] 和 items[3] = [5,3] 。
//子序列的总利润为 3 + 2 + 5 = 10 ，子序列包含 3 种不同类别 [1, 2, 3] 。
//因此，优雅度为 10 + 32 = 19 ，可以证明 19 是可以获得的最大优雅度。
//示例 3：
//
//输入：items = [[1,1],[2,1],[3,1]], k = 3
//输出：7
//解释：
//在这个例子中，我们需要选出长度为 3 的子序列。
//我们需要选中所有项目。
//子序列的总利润为 1 + 2 + 3 = 6，子序列包含 1 种不同类别 [1] 。
//因此，最大优雅度为 6 + 12 = 7 。
//
//
//提示：
//
//1 <= items.length == n <= 105
//items[i].length == 2
//items[i][0] == profiti
//items[i][1] == categoryi
//1 <= profiti <= 109
//1 <= categoryi <= n
//1 <= k <= n

func findMaximumElegance(items [][]int, k int) int64 {
	// items 按照 profit 从大到小排序
	slices.SortFunc(items, func(a, b []int) int {
		return b[0] - a[0]
	})
	ans, totalProfit := 0, 0
	vis := map[int]bool{} // 已累加的类型
	duplicate := []int{}  // 重复类别的利润
	for i, p := range items {
		profit, category := p[0], p[1]
		// 累加利润最高的前 k 个项目
		if i < k {
			totalProfit += profit
			if !vis[category] {
				vis[category] = true
			} else {
				duplicate = append(duplicate, profit)
			}
		} else if len(duplicate) > 0 && !vis[category] { // 之前没有的类别
			vis[category] = true                                // len(vis) 变大
			totalProfit += profit - duplicate[len(duplicate)-1] // 选一个重复类别中的最小利润替换, item 已经排过序了，所以duplicate也是从大到小
			duplicate = duplicate[:len(duplicate)-1]
		} // else：比前面的利润小，而且类别还重复了，选它只会让 totalProfit 变小，len(vis) 不变，优雅度不会变大
		ans = max(ans, totalProfit+len(vis)*len(vis))
	}
	return int64(ans)
}

func max(a, b int) int {
	if a < b {
		return b
	}
	return a
}

func main() {
	items := [][]int{
		{3, 2},
		{5, 1},
		{10, 1},
	}
	k := 2
	println("case 1 = ", findMaximumElegance(items, k))

	items = [][]int{
		{1, 1},
		{2, 1},
		{3, 1},
	}
	k = 3
	println("case 2 = ", findMaximumElegance(items, k))
}
