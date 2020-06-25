package myCodePractice.zuo.advance.class02;

public class LongestIncreasingPathInMatrix {
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

    //每个位置都可以作为出发点,并且走过的路径必须递增
    //在递归中添加条件
    public static int getLongestIncreasingPathInMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLen = Math.max(maxLen, process(matrix, i, j));
            }
        }
        return maxLen;
    }
    //递归函数功能:
    //给定一个矩阵和出发点,返回从该点出发的最长的递增路径长度
    //比较特殊,base case在条件中
    public static int process(int[][] matrix, int row, int col) {
        //出发点一定算一个
        int res = 1;
        //找到往上下左右走得到的路径长度值中的最大值
        //刚开始输入的row与col一定是合法的,所以只要判断下一步能不能越界与符合条件即可
        //因为递归函数的含义为从该点出发,所以下一个点对应的要+1
        if (row - 1 > -1 && matrix[row - 1][col] > matrix[row][col]) {
            res = Math.max(res, 1 + process(matrix, row - 1, col));
        }
        //因为上下左右都是要走的,所以应该用多个if语句,而不是if else语句
        if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
            res = Math.max(res, 1 + process(matrix, row + 1, col));
        }
        if (col - 1 > -1 && matrix[row][col - 1] > matrix[row][col]) {
            res = Math.max(res, 1 + process(matrix, row, col - 1));
        }
        if (col + 1 < matrix[0].length && matrix[row][col + 1] > matrix[row][col]){
            res = Math.max(res, 1 + process(matrix, row, col + 1));
        }
        return res;
    }
}
