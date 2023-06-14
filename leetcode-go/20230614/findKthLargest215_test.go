package _0230614

import (
	"math/rand"
	"testing"
)

func findKthLargest(nums []int, k int) int {
	return quickSort(nums, 0, len(nums)-1, len(nums)-k)
}

func quickSort(nums []int, l, r, k int) int {
	index := randomPartition(nums, l, r)
	if index == k {
		return nums[k]
	} else if index < k {
		return quickSort(nums, index+1, r, k)
	} else {
		return quickSort(nums, l, index-1, k)
	}
}

func randomPartition(nums []int, l, r int) int {
	index := l + rand.Intn(r-l+1)
	nums[l], nums[index] = nums[index], nums[l]
	return partition(nums, l, r)
}

func partition(nums []int, l, r int) int {
	p, index := nums[r], l-1

	for i := l; i < r; i++ {
		if nums[i] < p {
			index++
			nums[i], nums[index] = nums[index], nums[i]
		}
	}

	nums[r], nums[index+1] = nums[index+1], nums[r]
	return index + 1
}

func TestFindKthLargest(t *testing.T) {
	nums1 := []int{3, 2, 1, 5, 6, 4}
	nums2 := []int{3, 2, 3, 1, 2, 4, 5, 5, 6}

	t.Log(findKthLargest(nums1, 2))
	t.Log(findKthLargest(nums2, 4))
}
