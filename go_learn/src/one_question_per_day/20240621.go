package main

import "math"

// https://leetcode.cn/problems/6CE719/description/  LCP 61. 气温变化趋势
func temperatureTrend(temperatureA []int, temperatureB []int) int {

	ans := 1
	n := len(temperatureA)
	length := 1
	for i := 1; i < n; i++ {
		if (temperatureA[i] == temperatureA[i-1] && temperatureB[i] == temperatureB[i-1]) || (temperatureA[i] < temperatureA[i-1] && temperatureB[i] < temperatureB[i-1]) || (temperatureA[i] > temperatureA[i-1] && temperatureB[i] > temperatureB[i-1]) {
			length++
			if length > ans {
				ans = length
			}
		} else {
			length = 1
		}
	}
	return ans
}

// https://leetcode.cn/problems/find-the-child-who-has-the-ball-after-k-seconds/  3178. 找出 K 秒后拿着球的孩子
func numberOfChild(n int, k int) int {
	if k < n {
		return k
	}

	lop := k / (n - 1)
	start := 0
	if lop%2 != 0 {
		start = n - 1
	}

	if k%(n-1) == 0 {
		return start
	} else {
		return int(math.Abs(float64(start - int(k%(n-1)))))
	}

	//d := k % (2 * (n - 1))
	//if d > n-1 {
	//	return 2*(n-1) - d
	//}
	//return d
}

// https://leetcode.cn/problems/find-the-n-th-value-after-k-seconds/  3179. K 秒后第 N 个元素的值
func valueAfterKSeconds(n int, k int) int {
	ans := 0
	for i := n; i > 0; i-- {
		ans += i * k
		k = k + 1
	}
	return ans
}

func main() {
	temperatureA := []int{21, 18, 18, 18, 31}
	temperatureB := []int{34, 32, 16, 16, 17}

	println(temperatureTrend(temperatureA, temperatureB))

	println(numberOfChild(3, 5)) // 1
	println(numberOfChild(5, 6)) // 2
	println(numberOfChild(4, 2)) // 2

	println(valueAfterKSeconds(4, 5)) // 56
	println(valueAfterKSeconds(5, 3)) // 35

}
