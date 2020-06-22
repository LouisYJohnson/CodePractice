package myCodePractice.zuo.basic.class07;

public class MinPath {
    //给你一个二维数组， 二维数组中的每个数都是正数， 要求从左上
    //角走到右下角， 每一步只能向右或者向下。 沿途经过的数字要累
    //加起来。 返回最小的路径和。
    //这个大问题可以分为逻辑相同的小问题,即在当前点走到右下角的最小路径,等于当前点向右走或者向下走的
    //路径中的最小路径,子问题与父问题逻辑相同
//    public static int process(int[][] arr, int row, int col) {
//        //base case
//        //不能这么写,这么写没有限制只能向右或者向下走
//        if (row < 0 || row > arr.length - 1 || col < 0 || col > arr[0].length - 1) return 0;
//
//        int cur = arr[row][col];
//        return cur + Math.min(process(arr, row + 1, col), process(arr, row, col + 1));
//    }
    public static int process(int[][] arr, int row, int col) {
        //base case
        //必须走到右下角才算是结尾
        if (row == arr.length - 1 && col == arr[0].length - 1) return arr[row][col];

        int cur = arr[row][col];
        if (row == arr.length - 1 && col < arr[0].length) { //碰到了下边
            return cur + process(arr, row, col + 1);
        }
        if (col == arr[0].length - 1 && row < arr.length) { //碰到了右边
            return cur + process(arr, row + 1, col);
        }
        return cur + Math.min(process(arr, row + 1, col), process(arr, row, col + 1));
    }


    public static int minPath1(int[][] matrix) {
        return process1(matrix, 0, 0);
    }

    public static int process1(int[][] matrix, int i, int j) {
        //base case:
        if (i == matrix.length - 1 && j == matrix[0].length - 1) return matrix[i][j];
        if (i == matrix.length - 1) {
            return matrix[i][j] + process1(matrix, i, j + 1);
        } else if (j == matrix[0].length - 1) {
            return matrix[i][j] + process1(matrix, i + 1, j);
        }
        return matrix[i][j] + Math.min(process1(matrix, i + 1, j), process1(matrix, i, j + 1));
    }

    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(minPath1(m));
        System.out.println(process(m, 0, 0));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(process(m, 0, 0));
    }

}
