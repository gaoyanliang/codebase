package main

import "golang.org/x/exp/slices"

// https://leetcode.cn/problems/maximum-total-damage-with-spell-casting/description/  3186. 施咒的最大总伤害
func maximumTotalDamage(power []int) int64 {
	powermap := map[int]int{}
	for _, p := range power {
		powermap[p]++
	}
	n := len(powermap)
	a := make([]int, 0, n)
	for x := range powermap {
		a = append(a, x)
	}
	slices.Sort(a)

	j := 0
	dp := make([]int, n+1)
	for i, x := range a {
		for a[j] < x-2 {
			j++
		}
		dp[i+1] = maxxx(dp[i], dp[j]+x*powermap[x])
	}
	return int64(dp[n])
}

// https://leetcode.cn/problems/delete-and-earn/description/  740. 删除并获得点数
func deleteAndEarn(power []int) int {
	powermap := map[int]int{}
	for _, p := range power {
		powermap[p]++
	}
	n := len(powermap)
	a := make([]int, 0, n)
	for x := range powermap {
		a = append(a, x)
	}
	slices.Sort(a)

	j := 0
	dp := make([]int, n+1)
	for i, x := range a {
		for a[j] < x-1 {
			j++
		}
		dp[i+1] = maxxx(dp[i], dp[j]+x*powermap[x])
	}
	return dp[n]
}

// https://leetcode.cn/problems/house-robber/ 198. 打家劫舍
func rob(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	if n == 2 {
		return maxxx(nums[0], nums[1])
	}

	dp := make([]int, n+1)
	dp[0] = nums[0]
	dp[1] = maxxx(nums[0], nums[1])
	for i, num := range nums {
		if i < 2 {
			continue
		}
		dp[i] = maxxx(dp[i-1], dp[i-2]+num)
	}
	return dp[n-1]
}

// https://leetcode.cn/problems/maximum-product-subarray/  152. 乘积最大子数组
func maxProduct(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	if n == 2 {
		return maxxx(nums[0]*nums[1], maxxx(nums[0], nums[1]))
	}
	dp := [2]int{0, 0}
	ans := 0
	for i := 0; i < n; i++ {
		if nums[i] == 0 {
			dp[0], dp[1] = 0, 0
			continue
		}
		if nums[i] < 0 {
			dp[1] = min(nums[i], dp[0]*nums[i])
		}
		dp[0] = maxxx(1, maxxx(nums[i], dp[0]*nums[i]))
		ans = maxxx(ans, dp[0])
	}
	return ans
}

func maxxx(a, b int) int {
	if a < b {
		return b
	}
	return a
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func main() {
	power := []int{1, 1, 3, 4}
	println(maximumTotalDamage(power))

	power1 := []int{7, 1, 6, 6}
	println(maximumTotalDamage(power1))

	power2 := []int{3, 4, 2}
	println(deleteAndEarn(power2))

	power3 := []int{2, 2, 3, 3, 3, 4}
	println(deleteAndEarn(power3))

	power4 := []int{1, 2, 3, 1}
	println(rob(power4)) // 4

	power5 := []int{2, 7, 9, 3, 1}
	println(rob(power5)) // 12

	power6 := []int{2, 1, 1, 2}
	println(rob(power6)) // 4

	power7 := []int{2, 3, -2, 4}
	println(maxProduct(power7)) // 6

	power8 := []int{-2, 0, 1}
	println(maxProduct(power8)) // 0

}
