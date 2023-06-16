package _0230616

import (
	"math"
	"testing"
)

func TestMinOperations(t *testing.T) {
	//t.Log(minOperations1([]int{5, 6, 7, 8, 9}, 4))
	t.Log(minOperations1([]int{1, 1, 4, 2, 3}, 5))
}

func minOperations1(nums []int, x int) int {
	left, sum, target, ret := 0, 0, 0, math.MaxInt
	for _, num := range nums {
		sum += num
	}

	target = sum - x
	if target < 0 {
		return -1
	}
	if target == 0 {
		return len(nums)
	}

	sum = 0
	for right, num := range nums {
		sum += num
		for sum >= target {
			if sum == target {
				ret = min(ret, len(nums)-(right-left+1))
			}
			sum -= nums[left]
			left++
		}

	}
	if ret == math.MaxInt {
		return -1
	}

	return ret
}

func minOperations(nums []int, x int) int {
	left, sum, target, ret := 0, 0, 0, -1
	for _, num := range nums {
		sum += num
	}

	target = sum - x
	if target < 0 {
		return -1
	}
	if target == 0 {
		return len(nums)
	}

	sum = 0
	for right, num := range nums {
		sum += num
		for sum > target {
			sum -= nums[left]
			left++
		}
		if sum == target {
			ret = max(ret, right-left+1)
		}
	}

	return len(nums) - ret
}
