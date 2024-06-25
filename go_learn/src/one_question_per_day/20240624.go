package main

import "fmt"

// https://leetcode.cn/problems/next-greater-element-i/description/  496. 下一个更大元素 I
func nextGreaterElement(nums1 []int, nums2 []int) []int {
	ans := []int{}
	dict := map[int]int{}
	for i := len(nums2) - 1; i >= 0; i-- {
		for len(ans) > 0 && ans[len(ans)-1] < nums2[i] {
			ans = ans[:len(ans)-1]
		}
		dict[nums2[i]] = -1
		if len(ans) > 0 {
			dict[nums2[i]] = ans[len(ans)-1]
		}
		ans = append(ans, nums2[i])
	}
	ret := []int{}
	for i := 0; i < len(nums1); i++ {
		ret = append(ret, dict[nums1[i]])
	}
	return ret
}

// https://leetcode.cn/problems/next-greater-element-ii/  503. 下一个更大元素 II
func nextGreaterElements(nums []int) []int {
	stack := []int{}
	n := len(nums)
	ret := make([]int, n)
	for i := range nums {
		ret[i] = -1
	}
	for i := 2*n - 1; i >= 0; i-- {
		for len(stack) > 0 && stack[len(stack)-1] <= nums[i%n] {
			stack = stack[:len(stack)-1]
		}
		if i < n && len(stack) > 0 {
			ret[i] = stack[len(stack)-1]
		}
		stack = append(stack, nums[i%n])
	}
	return ret
}

func main() {
	nums1 := []int{4, 1, 2}
	nums2 := []int{1, 3, 4, 2}

	ans := nextGreaterElement(nums1, nums2)
	fmt.Println(ans)

	nums1 = []int{2, 4}
	nums2 = []int{1, 2, 3, 4}
	ans = nextGreaterElement(nums1, nums2)
	fmt.Println(ans)

	nums := []int{1, 2, 1}
	fmt.Println(nextGreaterElements(nums))

	nums = []int{1, 2, 3, 4, 3}
	fmt.Println(nextGreaterElements(nums))
}
