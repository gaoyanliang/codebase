package _0230614

import "testing"

//https://leetcode.cn/problems/get-kth-magic-number-lcci/
//
//面试题 17.09. 第 k 个数
//有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
//
//示例 1:
//
//输入: k = 5
//
//输出: 9

func getKthMagicNumber(k int) int {
	dp := make([]int, k+1)
	num3, num5, num7 := 1, 1, 1

	var n3, n5, n7 int

	dp[1] = 1
	for i := 2; i <= k; i++ {
		n3 = dp[num3] * 3
		n5 = dp[num5] * 5
		n7 = dp[num7] * 7
		dp[i] = min(n3, min(n5, n7))

		if n3 == dp[i] {
			num3++
		}
		if n5 == dp[i] {
			num5++
		}
		if n7 == dp[i] {
			num7++
		}
	}

	return dp[k]
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func TestGetKthMagicNumber(t *testing.T) {
	t.Log(getKthMagicNumber(5))
}
