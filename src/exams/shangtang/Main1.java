package exams.shangtang;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n == 0 || m == 0) {
            System.out.println(0);
            return;
        }
        int[][] mat = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int x = sc.nextInt();
                mat[i][j] = x;
            }
        }

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxLen = Math.max(maxLen, process(mat, i, j));
            }
        }

        System.out.println(maxLen);
    }

    public static int process(int[][] mat, int row, int col) {
        int res = 1;

        if (row - 1 > -1 && mat[row - 1][col] > mat[row][col]) {
            res = Math.max(res, 1 + process(mat, row - 1, col));
        }

        if (row + 1 < mat.length && mat[row + 1][col] > mat[row][col]) {
            res = Math.max(res, 1 + process(mat, row + 1, col));
        }

        if (col - 1 > -1 && mat[row][col - 1] > mat[row][col]) {
            res = Math.max(res, 1 + process(mat, row, col - 1));
        }

        if (col + 1 < mat[0].length && mat[row][col + 1] > mat[row][col]) {
            res = Math.max(res, 1 + process(mat, row, col + 1));
        }
        return res;
    }
}
