package com.newcoder.zuo3.gaopin.class03;

public class Problem_02_Islands {
    //给定一个二维数组，所有位置的值不是0就是1。规定每个位置
    //可以和它上下左右位置上的值相连。
    //有一个叫做岛的概念，定义如下：
    //连成一片的1，如果周围都是0，那么这一片1，构成一个岛。
    //求整张图上有多少个岛。
    //例如：
    //0 0 0 0 0 0 0 0 0
    //0 1 1 0 0 1 1 1 0
    //0 1 1 1 0 0 0 1 0
    //0 1 1 0 0 0 0 0 0
    //0 0 0 0 0 1 1 0 0
    //0 0 0 0 1 1 1 0 0
    //000000000
    //这张图上有三个岛
    //0 0 0 0 0 0 0 0 0
    //0 1 1 0 1 1 1 1 0
    //0 1 1 1 1 0 0 1 0
    //0 1 1 0 0 0 0 1 0
    //0 0 0 0 0 1 1 1 0
    //0 0 0 0 1 1 1 0 0
    //0 0 0 0 0 0 0 0 0
    //这张图上有一个岛。

    //用递归来做,遍历所有的点,递归函数功能为:碰到1就设置为2并向上下左右四个方向进行修改
    public static int countIslands(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;

        int rows = m.length;
        int cols = m[0].length;
        int res = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    infect(m, i, j, rows, cols);
                    res++;
                }
            }
        }
        return res;
    }

    public static void infect(int[][] m, int i, int j, int rows, int cols) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || m[i][j] != 1) return;
        m[i][j] = 2;
        infect(m, i + 1, j, rows, cols);
        infect(m, i, j + 1, rows, cols);
        infect(m, i - 1, j, rows, cols);
        infect(m, i, j - 1, rows, cols);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));

    }

}
