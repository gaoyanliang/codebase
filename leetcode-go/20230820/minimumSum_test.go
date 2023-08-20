package _0230820

import (
	"fmt"
	"testing"
)

// 6450. k-avoiding 数组的最小总和
// 给你两个整数 n 和 k 。
//
// 对于一个由 不同 正整数组成的数组，如果其中不存在任何求和等于 k 的不同元素对，则称其为 k-avoiding 数组。
//
// 返回长度为 n 的 k-avoiding 数组的可能的最小总和。
//
// 示例 1：
//
// 输入：n = 5, k = 4
// 输出：18
// 解释：设若 k-avoiding 数组为 [1,2,4,5,6] ，其元素总和为 18 。
// 可以证明不存在总和小于 18 的 k-avoiding 数组。
// 示例 2：
//
// 输入：n = 2, k = 6
// 输出：3
// 解释：可以构造数组 [1,2] ，其元素总和为 3 。
// 可以证明不存在总和小于 3 的 k-avoiding 数组。
//
// 提示：
//
// 1 <= n, k <= 50
func minimumSum(n int, k int) int {
	var sum = n * (1 + n) / 2
	if k < n {
		var i, j, tmp int
		j = 1
		for i = k; i > 0; i-- {
			tmp = i
			for k-tmp > 0 && k-tmp < tmp {
				sum -= tmp
				sum += n + j
				tmp = n + j
				j++
			}
			//if k-i > 0 && k-i < i {
			//	sum -= i
			//	sum += n + j
			//	j++
			//}
		}
		return sum
	}

	if k <= 2*n-1 {
		var i, j, tmp int
		j = 1
		for i = n; i > 0; i-- {
			tmp = i
			for k-tmp > 0 && k-tmp < tmp {
				sum -= tmp
				sum += n + j
				tmp = n + j
				j++
			}
			//if k-i > 0 && k-i < i {
			//	sum -= i
			//	sum += n + j
			//	j++
			//}
		}
		return sum
	}

	return sum
}

func TestMinimumSum(t *testing.T) {

	fmt.Println(minimumSum(5, 4)) // 18
	fmt.Println(minimumSum(2, 6)) // 3
	fmt.Println(minimumSum(2, 3)) // 4
	fmt.Println(minimumSum(3, 5)) // 8
}
