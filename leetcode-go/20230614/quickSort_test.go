package _0230614

//// 快速排序1
//func QuickSort1(A []int, start, end int) {
//	if start < end {
//		piv_pos := randomPartition(A, start, end)
//		QuickSort1(A, start, piv_pos-1)
//		QuickSort1(A, piv_pos+1, end)
//	}
//}
//
//func partition(A []int, start, end int) int {
//	i, piv := start, A[end] // 从第一个数开始扫描，选取最后一位数字最为对比
//	for j := start; j < end; j++ {
//		if A[j] < piv { //  A[j]逆序：A[i] < piv < A[j]
//			if i != j { // 不是同一个数
//				A[i], A[j] = A[j], A[i] // A[j] 放在正确的位置
//			}
//			i++ //扫描下一个数
//		}
//	}
//	A[i], A[end] = A[end], A[i] // A[end] 回到正确的位置
//	return i
//}
//
//func randomPartition(A []int, start, end int) int {
//	random := rand.Int()%(end-start+1) + start
//	A[random], A[end] = A[end], A[random]
//	return partition(A, start, end)
//}

// ---------------------------------------------------

//func sortArray(nums []int) []int {
//	quickSort(nums, 0, len(nums)-1)
//	return nums
//}
//
//func quickSort(nums []int, l, r int) {
//	if l < r {
//		index := randomPartition(nums, l, r)
//		quickSort(nums, l, index-1)
//		quickSort(nums, index+1, r)
//	}
//}
//
//func randomPartition(nums []int, l, r int) int {
//	index := l + rand.Intn(r-l+1)
//	nums[index], nums[l] = nums[l], nums[index]
//	return partition(nums, l, r)
//}
//
//func partition(nums []int, l, r int) int {
//
//	index, p := l-1, nums[r]
//	for i := l; i < r; i++ {
//		if nums[i] < p {
//			index++
//			nums[i], nums[index] = nums[index], nums[i]
//		}
//	}
//	index++
//	nums[index], nums[r] = nums[r], nums[index]
//	return index
//}
