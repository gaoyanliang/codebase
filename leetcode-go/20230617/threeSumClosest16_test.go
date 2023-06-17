package _0230617

import (
	"math"
	"sort"
	"testing"
)

func TestThreeSumClosest(t *testing.T) {
	t.Log(threeSumClosest([]int{-1, 2, 1, -4}, 1))
	t.Log(threeSumClosest([]int{0, 0, 0}, 1))
	t.Log(threeSumClosest([]int{0, 1, 2}, 0))
	t.Log(threeSumClosest([]int{1, 1, 1, 1}, 4))
}

func threeSumClosest(nums []int, target int) int {

	sort.Ints(nums)
	ret, sum := math.MaxInt32, 0
	update := func() {
		if abs(sum-target) < abs(ret-target) {
			ret = sum
		}
	}
	for first := 0; first < len(nums)-2; first++ {
		if first > 0 && nums[first] == nums[first-1] {
			continue
		}

		second, thrid := first+1, len(nums)-1
		for second < thrid {
			if second > first+1 && nums[second] == nums[second-1] {
				second++
				continue
			}

			sum = nums[first] + nums[second] + nums[thrid]

			update()

			if sum == target {
				return target
			} else if sum < target {
				second++
			} else {
				thrid--
			}
		}
	}
	return ret
}

func abs(x int) int {
	if x < 0 {
		return -1 * x
	}
	return x
}
