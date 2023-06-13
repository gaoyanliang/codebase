package _0230613

import (
	"testing"
)

// https://leetcode.cn/problems/distinct-subsequences/
//115. 不同的子序列
//给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
//
//题目数据保证答案符合 32 位带符号整数范围。
//
//
//
//示例 1：
//
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//rabbbit
//rabbbit
//rabbbit
//示例 2：
//
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
//
//
//提示：
//
//1 <= s.length, t.length <= 1000
//s 和 t 由英文字母组成

func numDistinct(s string, t string) int {
	sLen, tLen := len(s), len(t)

	dp := make([][]int, sLen+1)
	for i := range dp {
		dp[i] = make([]int, tLen+1)
	}

	for i := 0; i <= sLen; i++ {
		for j := 0; j <= tLen; j++ {
			if j == 0 {
				dp[i][j] = 1
			} else if i == 0 {
				dp[i][j] = 0
			} else {
				if s[i-1] == t[j-1] {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
				} else {
					dp[i][j] = dp[i-1][j]
				}
			}
		}
	}
	return dp[sLen][tLen]
}

func TestNumDistinct(t *testing.T) {
	var (
		s1 = "babgbag"
		t1 = "bag"

		s2 = "rabbbit"
		t2 = "rabbit"
	)
	t.Log(numDistinct(s2, t2))
	t.Log(numDistinct(s1, t1))

}
