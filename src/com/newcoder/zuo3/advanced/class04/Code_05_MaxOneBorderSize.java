package com.newcoder.zuo3.advanced.class04;

public class Code_05_MaxOneBorderSize {
    //边界都是1的最大正方形大小
    //【题目】
    //给定一个NN的矩阵matrix， 在这个矩阵中， 只有0和1两种值， 返回边框全是1的最大正方
    //形的边长长度。
    //例如：
    //0 1 1 1 1
    //0 1 0 0 1
    //0 1 0 0 1
    //0 1 1 1 1
    //0 1 0 1 1
    //其中， 边框全是1的最大正方形的大小为4*4， 所以返回4。

    //笨办法:找到所有正方形,穷举所有的正方形起始点,找起始点开始的所有可能边长的正方形
    //
    //好方法:预处理数组,构建两个数组,大小和原矩阵相同,分别为down矩阵和right矩阵,
    // 矩阵中的值表示包括这个位置在内下方或者右边有多少个连续的1,如果自身是0,那么就是0
    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        for (int j = 0; j < m[0].length; j++) {
            for (int i = m.length - 1; i >= 0; i--) {
                if (i + 1 == m.length) {
                    down[i][j] = m[i][j] == 1 ? 1 : 0;
                } else {
                    down[i][j] = m[i][j] == 1 ? 1 + down[i + 1][j] : 0;
                }
            }
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = m[0].length - 1; j >= 0; j--) {
                if (j + 1 == m[0].length) {
                    right[i][j] = m[i][j] == 1 ? 1 : 0;
                } else {
                    right[i][j] = m[i][j] == 1 ? 1 + right[i][j + 1] : 0;
                }
            }
        }
    }

    public static int getMaxSize(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
//        printMatrix(right);
//        System.out.println();
//        printMatrix(down);
        for (int size = Math.min(m.length, m[0].length); size > 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size;
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i < right.length; i++) {
            for (int j = 0; j < right[0].length; j++) {
                if (Math.min(right[i][j], down[i][j]) >= size) {
                    int rightEleDown = down[i][j + right[i][j] - 1];
                    int downEleRight = right[i + down[i][j] - 1][j];
                    if (Math.min(rightEleDown, downEleRight) >= size) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //for Combinations
    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println();
        System.out.println(getMaxSize(matrix));
    }

}
