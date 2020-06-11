package com.newcoder.zuo3.advanced.class02;

public class Code_05_Longest_Increasing_Path_in_a_Matrix {
    //给定一个整数矩阵matrix， 每个位置你可以向左、 右、 下、 上移动， 找到其中最长的递增路径。
    //例如：
    //matrix = [
    //[9,9,4],
    //[6,6,8],
    //[2,1,1]
    //] 返
    //回4
    //最长路径是[1, 2, 6, 9].
    //matrix = [
    //[3,4,5],
    //[3,2,6],
    //[2,2,1]
    //] 返
    //回4
    //最长路径是[1, 2, 6, 9].
    //可以用暴力递归改成动态规划
    //先实现暴力递归:
    public static int longestIncreasingPath(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        //每个位置都作为初始位置尝试一下,找到其中的最大路径值
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[0].length; col++) {
                max = Math.max(max,process(m,row,col));
            }
        }
        return max;
    }

    //同类问题处理逻辑完全一致,只是规模缩小了,所以可以使用暴力递归
    //同类问题:都是在一个点上应该往哪里走,而且往哪里走的限制条件都是一模一样的
    public static int process(int[][] m,int row, int col) {
        //从起点开始则包括起点,所以path初始值为1
        int path = 1;
        //子过程分别为,向上下左右走返回的最长递增路径
        //向上走:不越界且递增才能走!
        if (row - 1 >= 0 && m[row - 1][col] > m[row][col]) {
            //process中的这个+1表示当前位置走过了所以放了一个1,因为process如果下一层递归不能继续向下走了,就返回1
            path = Math.max(path,process(m,row - 1,col) + 1);
        }
        //向下走
        if (row + 1 <= m.length - 1 && m[row + 1][col] > m[row][col]) {
            path = Math.max(path,process(m,row + 1,col) + 1);
        }
        //向左走
        if (col - 1 >= 0 && m[row][col - 1] > m[row][col]) {
            path = Math.max(path,process(m,row,col - 1) + 1);
        }
        //向右走
        if (col + 1 <= m[0].length - 1 && m[row][col + 1] > m[row][col]) {
            path = Math.max(path,process(m,row,col + 1) + 1);
        }
        return path;
    }
    //for test
    public static void main(String[] args) {
        int[][] arr = {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };
        System.out.println(longestIncreasingPath(arr));
    }

}
