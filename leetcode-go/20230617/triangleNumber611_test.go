package _0230617

import (
	"sort"
	"testing"
)

func TestTriangleNumber(t *testing.T) {
	t.Log(triangleNumber([]int{2, 2, 3, 4}))
	t.Log(triangleNumber([]int{4, 2, 3, 4}))
}

func triangleNumber(nums []int) int {
	sort.Ints(nums)
	ret := 0

	// 排序后 a <= b <= c  a + b > c 即可满足三角
	for i := 0; i < len(nums); i++ {
		for j, k := i-1, 0; k < j; j-- {
			for k < j && nums[i] >= nums[j]+nums[k] {
				k++
			}
			ret += j - k
		}
	}
	return ret
}
