package main

import "golang.org/x/exp/slices"

func maxTotalReward(rewardValues []int) int {
	slices.Sort(rewardValues)
	ans := 0
	for i := 0; i < len(rewardValues)-1; i++ {
		if ans+rewardValues[i] < rewardValues[i+1] {
			ans = ans + rewardValues[i]
		}
	}
	if ans < rewardValues[len(rewardValues)-1] {
		return ans + rewardValues[len(rewardValues)-1]
	}
	return ans
}

func main() {
	a := []int{1, 1, 3, 3}
	println(maxTotalReward(a))

	a = []int{1, 6, 4, 3, 2}
	println(maxTotalReward(a))

}
