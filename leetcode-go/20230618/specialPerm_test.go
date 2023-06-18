package _0230618

//func specialPerm(nums []int) int {
//	table := map[int]bool{}
//	arr := map[int][]int{}
//
//	initTable := func() {
//		for _, num := range nums {
//			table[num] = false
//		}
//	}
//
//	for i := 0; i < len(nums); i++ {
//		arr[nums[i]] = []int{}
//		for j := 0; j < len(nums); j++ {
//			if i == j {
//				continue
//			}
//			if nums[i]%nums[j] == 0 || nums[j]%nums[i] == 0 {
//				arr[nums[i]] = append(arr[nums[i]], nums[j])
//			}
//		}
//	}
//
//}
//
//func initTable(nums []int, table map[int]bool) {
//
//}
