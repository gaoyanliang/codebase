package _0230616

import "testing"

func TestNumSubarrayProductLessThank(t *testing.T) {
	t.Log(numSubarrayProductLessThanK([]int{10, 5, 2, 6}, 100))
	t.Log(numSubarrayProductLessThanK([]int{1, 2, 3}, 0))
}

func numSubarrayProductLessThanK(nums []int, k int) (ans int) {
	left, sum := 0, 1

	for right, num := range nums {
		sum *= num
		if sum >= k {
			for left <= right && sum >= k {
				sum /= nums[left]
				left++
			}
		}
		ans += right - left + 1
	}
	return
}
