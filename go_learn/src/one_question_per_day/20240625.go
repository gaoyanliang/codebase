package main

import "fmt"

func minimumArea(grid [][]int) int {
	// 定位矩形的四个角
	left, right := len(grid[0]), 0
	top, bottom := len(grid), 0

	for i, gi := range grid {
		for j, cur := range gi {
			if cur == 1 {
				left = compare(left, j, false)
				right = compare(right, j, true)
				top = compare(top, i, false)
				bottom = i
			}
		}
	}
	return (right - left + 1) * (bottom - top + 1)
}

func compare(a, b int, isMax bool) int {
	if isMax {
		if a > b {
			return a
		} else {
			return b
		}
	} else {
		if a > b {
			return b
		} else {
			return a
		}
	}
}

func main() {
	grid := [][]int{{0, 1, 0}, {1, 0, 1}}
	fmt.Println(minimumArea(grid))

	grid = [][]int{{1, 0}, {0, 0}}
	fmt.Println(minimumArea(grid))

}
