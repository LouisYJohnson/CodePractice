package com.newcoder.zuo3.basic.class07;

public class MinPath {
    //给你一个二维数组， 二维数组中的每个数都是正数， 要求从左上
    //角走到右下角， 每一步只能向右或者向下。 沿途经过的数字要累
    //加起来。 返回最小的路径和。
    //这个大问题可以分为逻辑相同的小问题,即在当前点走到右下角的最小路径,等于当前点向右走或者向下走的
    //路径中的最小路径,子问题与父问题逻辑相同
    public static int minPath1(int[][] matrix) {
        return process1(matrix,0,0);
    }
    //递归版:
    //明确函数功能:从i,j走到矩阵右下角的最小路径
    //所以参数应该为matrix,i,j
    public static int process1(int[][] matrix, int i, int j) {
        //base case:
        if (i == matrix.length-1 && j == matrix[0].length-1) return matrix[i][j];
        //如果碰到下边界或右边界,只能横着走或者竖着走
        if (i == matrix.length-1) {//碰到下边界,只能横着走
            return matrix[i][j] + process1(matrix,i,j+1);
        }else if (j == matrix[0].length-1) {//碰到右边界,只能竖着走
            return matrix[i][j] + process1(matrix,i+1,j);
        }
        //如果都没碰到,就是找右走或者下走的最小路径点
        return matrix[i][j] + Math.min(process1(matrix,i+1,j),process1(matrix,i,j+1));
    }

    //非递归版:
    //只要i,j确定了,最小值就确定了,而i,j的变化范围为矩阵matrix的行和列值
    //构建一个矩阵行列大小对应的解空间,解空间的0,0位置,是我们要的结果
    //而看base case发现解空间中的右下角,是谁都不依赖的点,大小为matrix[i][j]
    //看递归条件(递归条件就是我的计算公式),发现如果是在下边界,左侧点依赖右侧点,所以反向计算可以从最后一行的右侧计算到最后一行的左侧
    //同理,从下到上计算最后一列
    //公式中,matrix对应原数组中的数,process1对应解空间中的数
    //然后看其他(i,j)处的点,需要依赖右下和坐上,所以可以选择从倒数第二行从右至左一行行或从倒数第二列从下之上一列列计算
    //此处选择一行行计算
    public static int minPath2(int[][] m) {
        int[][] solveSpace = new int[m.length][m[0].length];
        //先将谁都不依赖的点填好
        solveSpace[m.length-1][m[0].length-1] = m[m.length-1][m[0].length-1];
        //开始从右至左填最后一行
        for (int i = m[0].length-2; i >=0 ; i--) {
            solveSpace[m.length-1][i] = m[m.length-1][i] + solveSpace[m.length-1][i+1];
        }
        //从下至上填最后一列
        for (int i = m.length-2; i >= 0; i--) {
            solveSpace[i][m[0].length-1] = m[i][m[0].length-1] + solveSpace[i+1][m[0].length-1];
        }
        //对于其他的数,一行行的从右到左从倒数第二行开始计算
        for (int i = m.length-2; i >= 0; i--) {
            for (int j = m[0].length-2; j >= 0; j--) {
                solveSpace[i][j] = m[i][j] + Math.min(solveSpace[i+1][j],solveSpace[i][j+1]);
            }
        }
        return solveSpace[0][0];
    }
    // for test
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
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
}
