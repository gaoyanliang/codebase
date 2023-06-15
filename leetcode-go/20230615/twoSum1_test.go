package _0230615

func twoSum(nums []int, target int) []int {
	table := map[int]int{}
	length := len(nums)

	for i := 0; i < length; i++ {
		if val, ok := table[target-nums[i]]; ok {
			return []int{val, i}
		}
		table[nums[i]] = i
	}

	return nil
}
