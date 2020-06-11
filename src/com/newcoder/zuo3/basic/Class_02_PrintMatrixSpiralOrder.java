package com.newcoder.zuo3.basic;

public class Class_02_PrintMatrixSpiralOrder {
    //结构:使用矩阵的左上角与右下角的点作为限制于条件,转圈打印即可,每次打印完毕往中间缩一圈
    //功能一定要分开,外部负责判断坐上与右下角的点,内部负责转圈打印4

    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        while(tR <= dR && tC <= dC) {
            printEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }
    //每次根据两个点,打印两个点之间的一圈的数
    public static void printEdge(int[][] arr, int tR,int tC,int dR,int dC) {
        int tempTR = tR;
        int tempTC = tC;
        int tempDR = dR;
        int tempDC = dC;
        //一个横行
        if (tempTR == tempDR) {
            while (tempTC <= tempDC) {
                System.out.println(arr[tempTR][tempTC++] + " ");
            }
        }else if (tC == dC) { //一个竖行
            while (tempTR <= tempDR) {
                System.out.println(arr[tempTR++][tempTC] + " ");
            }
        }else { //不是竖行也不是横行,是一个圈
            while (tempTC < tempDC) {//横着来
                System.out.println(arr[tempTR][tempTC++] + " ");
            }
            while (tempTR < tempDR) {//竖着来
                System.out.println(arr[tempTR++][tempDC] + " ");
            }
            while (tempDC > tC) {//反向横着来
                System.out.println(arr[tempDR][tempDC--] + " ");
            }
            while (tempDR > tR) {//从下往上
                System.out.println(arr[tempDR--][tC] + " ");
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);

    }
}
