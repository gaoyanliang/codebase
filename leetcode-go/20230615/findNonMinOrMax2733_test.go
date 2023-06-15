package _0230615

import "sort"

func findNonMinOrMax(nums []int) int {
	if len(nums) < 3 {
		return -1
	}
	a := nums[:3]
	sort.Ints(a)
	return a[1]
}
