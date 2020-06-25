package com.newcoder.zuo3.advanced.class02;

public class Code_05_Longest_Increasing_Path_in_a_Matrix {
    public static int longestIncreasingPath(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[0].length; col++) {
                max = Math.max(max, process(m, row, col));
            }
        }
        return max;
    }

    public static int process(int[][] m, int row, int col) {
        int path = 1;
        if (row - 1 >= 0 && m[row - 1][col] > m[row][col]) {
            path = Math.max(path, process(m, row - 1, col) + 1);
        }
        if (row + 1 <= m.length - 1 && m[row + 1][col] > m[row][col]) {
            path = Math.max(path, process(m, row + 1, col) + 1);
        }
        if (col - 1 >= 0 && m[row][col - 1] > m[row][col]) {
            path = Math.max(path, process(m, row, col - 1) + 1);
        }
        if (col + 1 <= m[0].length - 1 && m[row][col + 1] > m[row][col]) {
            path = Math.max(path, process(m, row, col + 1) + 1);
        }
        return path;
    }

    //for test
    public static void main(String[] args) {
        int[][] arr = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        System.out.println(longestIncreasingPath(arr));
    }

}
