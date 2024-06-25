package main

//https://leetcode.cn/problems/longest-uncommon-subsequence-ii/description/
//522. 最长特殊序列 II
//中等
//给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
//
//特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
//
// s 的 子序列可以通过删去字符串 s 中的某些字符实现。
//
//例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
//
//
//示例 1：
//
//输入: strs = ["aba","cdc","eae"]
//输出: 3
//示例 2:
//
//输入: strs = ["aaa","aaa","aa"]
//输出: -1
//
//
//提示:
//
//2 <= strs.length <= 50
//1 <= strs[i].length <= 10
//strs[i] 只包含小写英文字母

func isSubseq(s, t string) bool {
	i := 0
	for _, c := range t {
		if s[i] == byte(c) {
			i++
			if i == len(s) {
				return true
			}
		}
	}
	return false
}

func findLUSlength(strs []string) int {
	ret := -1
next:
	for i, s := range strs {
		if len(s) <= ret {
			continue
		}

		for j, t := range strs {
			if j != i && isSubseq(s, t) {
				continue next
			}
		}
		ret = len(s)
	}
	return ret
}

// 3185. 构成整天的下标对数目 II   https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-ii/description/
func countCompleteDayPairs(hours []int) int64 {
	// 超时
	//ret := 0
	//for i := 0; i < len(hours); i++ {
	//	for j := i + 1; j < len(hours); j++ {
	//		if (hours[i]+hours[j])%24 == 0 {
	//			ret += 1
	//		}
	//	}
	//}
	//return int64(ret)

	// 正确解法

	// 记录对 24 取余之后剩余数字的个数
	cnt := [24]int{}
	ans := int64(0)
	for _, h := range hours {
		// 这里需要注意顺序，先查询 在更新数据 （满足 对数 要求 i < j）
		ans += int64(cnt[(24-h%24)%24])
		cnt[h%24]++
	}
	return ans
}

// 1497. 检查数组对是否可以被 k 整除  https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k/description/
func canArrange(arr []int, k int) bool {
	cnt := map[int]int{}
	for _, a := range arr {
		cnt[(a%k+k)%k]++
	}

	for i := 0; i < k; i++ {
		if cnt[i]%2 == 0 && cnt[i] != cnt[k-i] {
			return false
		}
	}
	return true
}

func main() {
	data := []string{"aba", "cdc", "eae"}
	println(findLUSlength(data))

	data1 := []string{"aaa", "aaa", "aa"}
	println(findLUSlength(data1))

	hours := []int{11, 13}
	println(countCompleteDayPairs(hours))

	arr := []int{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}
	println(canArrange(arr, 5))

	arr = []int{1, 2, 3, 4, 5, 6}
	println(canArrange(arr, 7))

	arr = []int{1, 2, 3, 4, 5, 6}
	println(canArrange(arr, 10))

	arr = []int{-1, 1, -2, 2, -3, 3, -4, 4}
	println(canArrange(arr, 3))

}
