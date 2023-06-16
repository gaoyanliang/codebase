package _0230615

import (
	"testing"
)

func TestMinSubArrayLen(t *testing.T) {
	nums1 := []int{2, 3, 1, 2, 4, 3}
	nums2 := []int{1, 4, 4}
	nums3 := []int{1, 1, 1, 1, 1, 1, 1, 1}
	nums4 := []int{1, 2, 3, 4, 5}

	t.Log(minSubArrayLen(7, nums1))
	t.Log(minSubArrayLen(4, nums2))
	t.Log(minSubArrayLen(11, nums3))
	t.Log(minSubArrayLen(15, nums4))
}

func minSubArrayLen(target int, nums []int) int {

	left, sum, ret := 0, 0, len(nums)+1
	for right, num := range nums {
		sum += num

		for sum >= target {
			ret = min(ret, right-left+1)
			sum -= nums[left]
			left++
		}
	}
	if ret > len(nums) {
		return 0
	}
	return ret
}
