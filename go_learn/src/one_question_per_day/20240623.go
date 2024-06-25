package main

import "golang.org/x/exp/slices"

func minimumAverage(nums []int) float64 {
	slices.Sort(nums)
	n := len(nums)
	l, r := 1, n
	ans := float64(99999)
	for l < r {
		if (float64(nums[r-1])+float64(nums[l-1]))/float64(2) < ans {
			ans = (float64(nums[r-1]) + float64(nums[l-1])) / float64(2)
		}
		l++
		r--
	}
	return ans
}

func main() {
	nums := []int{7, 8, 3, 4, 15, 13, 4, 1}
	println(minimumAverage(nums))

	nums1 := []int{1, 9, 8, 3, 10, 5}
	println(minimumAverage(nums1))

	nums2 := []int{1, 2, 3, 7, 8, 9}
	println(minimumAverage(nums2))
}
