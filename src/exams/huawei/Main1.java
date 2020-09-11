package exams.huawei;

import java.util.Scanner;

public class Main1 {
    public static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
    public static int rows, columns;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        int[][] pool = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                pool[i][j] = scanner.nextInt();
            }
        }
        System.out.println(longestIncreasingPath(pool));
    }
    public static int longestIncreasingPath(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        rows = mat.length;
        columns = mat[0].length;
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ans = Math.max(ans, process(mat, i, j, memo));
            }
        }
        return ans;
    }

    private static int process(int[][] mat, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        ++memo[i][j];
        for (int[] dir : dirs) {
            int newRow = i + dir[0];
            int newColumn = j + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && mat[newRow][newColumn] > mat[i][j]) {
                memo[i][j] = Math.max(memo[i][j], process(mat, newRow, newColumn, memo) + 1);
            }
        }
        return memo[i][j];
    }
}
