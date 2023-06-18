package _0230618

import "sort"

func findValueOfPartition(nums []int) int {
	sort.Ints(nums)
	length := len(nums) - 1
	ret := nums[length]

	for i := 1; i <= length; i++ {
		ret = min(ret, nums[i]-nums[i-1])
	}
	return ret
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
