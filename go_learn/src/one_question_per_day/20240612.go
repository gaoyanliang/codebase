package main

// https://leetcode.cn/problems/battleships-in-a-board/
//419. 甲板上的战舰
//中等
//给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
//
//战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
//输入：board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
//输入：board = [["."]]
//输出：0
//
//
//提示：
//
//m == board.length
//n == board[i].length
//1 <= m, n <= 200
//board[i][j] 是 '.' 或 'X'

//func countBattleships(board [][]byte) int {
//	m,n := len(board), len(board[0])
//	ans := 0
//	for i := 0; i < m; i++ {
//		for j := 0; j < n; j++ {
//			if board[i][j] == 'X' && (i > 0 && board[i-1][j] != 'X') && (j>0 && board[i][j-1] != 'X') {
//				ans += 1
//			}
//		}
//	}
//	return ans
//}

func countBattleships(board [][]byte) int {
	ans := 0
	for i, row := range board {
		for j, c := range row {
			if c == 'X' &&
				(j == 0 || row[j-1] != 'X') &&
				(i == 0 || board[i-1][j] != 'X') {
				ans++
			}
		}
	}
	return ans
}

func main() {
	// 初始化二维字节数组
	board := [][]byte{
		[]byte{'X', '.', '.', 'X'}, // "ABC"
		[]byte{'.', '.', '.', 'X'}, // "DEF"
		[]byte{'.', '.', '.', 'X'}, // "GHI"
	}

	board = [][]byte{
		{'X', '.', '.', 'X'}, // "ABC"
		{'.', '.', '.', 'X'}, // "DEF"
		{'.', '.', '.', 'X'}, // "GHI"
	}

	println(countBattleships(board))
}
