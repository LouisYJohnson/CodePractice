package com.newcoder.zuo3.basic;

public class Class_02_ZigZagPrintMatrix {
    //外层负责上下的坐标移动,内层负责两个斜线连接的数组之间的打印
    public static void printMatrixZigZag(int[][] matrix) {
        //规定两个点该如何走
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = true;
        //最好的方式是每打印完一次判断是否到边界,而不是在打印语句中++,如果在语句中++,没有办法同时判断变量的变化
        while (tR != endR + 1) {
            //坐标移动和打印最好不要放在一起,很容易乱,分开写,先打印,再移动坐标,然后判断,是最好的
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }

    }

    public static void printLevel(int[][] matrix,int tx,int ty,int dx,int dy,boolean isRerverse) {
        //isRerverse为true,从右上往左下,为false,从左下往右上
        if (isRerverse) {
            while (tx <= dx && ty >= dy) {
                System.out.println(matrix[tx++][ty--] + " ");
            }
        }else {
            while (tx <= dx && ty >= dy) {
                System.out.println(matrix[dx--][dy++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
