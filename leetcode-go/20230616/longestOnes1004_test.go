package _0230616

import "testing"

func TestLongestOnes(t *testing.T) {
	t.Log(longestOnes([]int{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2))
	t.Log(longestOnes([]int{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3))
}

func longestOnes(nums []int, k int) (ans int) {
	left, zero := 0, 0
	for right, num := range nums {
		if num == 0 {
			zero++
		}

		for zero > k {
			if nums[left] == 0 {
				zero--
			}
			left++
		}
		ans = max(ans, right-left+1)
	}
	return
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
