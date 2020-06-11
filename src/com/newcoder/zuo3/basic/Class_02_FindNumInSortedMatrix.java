package com.newcoder.zuo3.basic;

public class Class_02_FindNumInSortedMatrix {
    //在一个已经排序的矩阵中找到对应数字
    public static boolean isContains(int[][] arr,int num) {
        int arrRows = arr.length-1;
        int arrCols = arr[0].length-1;
        int startCols = arrCols;
        int startRows = 0;

        while (startCols >= 0 && startRows <= arr.length - 1) {
            if (arr[startRows][startCols] < num) {
                startRows++;
            }else if (arr[startRows][startCols] > num) {
                startCols--;
            }else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = -1;
        System.out.println(isContains(matrix, K));
    }
}
