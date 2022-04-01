package com.yanliang.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanliang
 */
public class SolveNQueens {

    public static void main(String[] args) {

    }

    static List<List<String>> res = new ArrayList();
    public static List<List<String>> solveNQueens(int n) {
        // build borad
        List<String> board = new ArrayList();
        for (int i = 0; i < n; i ++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j ++) {
                sb.append(".");
            }
            board.add(sb.toString());
        }


        return res;
    }

    public static void backtrack(List<String> board, int row) {
        if (row == board.size()) {
            res.add(new ArrayList(board));
            return ;
        }

        for (int i = 0; i < board.size(); i ++) {
            if (!vaild(board, row, i)) {
                continue;
            }

            String rowString = board.get(row);
            char[] chs = rowString.toCharArray();

            chs[i] = 'Q';
            board.set(row, new String(chs));
            backtrack(board, row + 1);
            chs[i] = '.';
            board.set(row, new String(chs));
        }
    }

    public static boolean vaild(List<String> board, int row, int col) {
        // check col
        for (int i = 0; i < row; i ++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        // check left up
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i --, j --) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        // check right up
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.size(); i --, j ++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }
}
