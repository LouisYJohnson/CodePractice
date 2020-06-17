package myCodePractice.zuo.basic.class07;

public class SubArrayMaxSum {
    //给你一个二维数组， 二维数组中的每个数都是正数， 要求从左上
    //角走到右下角， 每一步只能向右或者向下。 沿途经过的数字要累
    //加起来。 返回最小的路径和

    //递归函数功能:
    //传入二维数组和起点,返回最小的路径和
//    public static int process(int[][] arr, int row, int col) {
//        //base case
//        //用来判断越界,这种写法是错的,因为并没有限制必须移动到右下角
//        if (row < 0 || row > arr.length - 1 || col < 0 || col > arr[0].length - 1) return 0;
//
//        int cur = arr[row][col];
//        return cur + Math.min(process(arr, row + 1, col), process(arr, row, col + 1));
//    }
    //递归函数功能:
    //传入二维数组和起点,返回最小的路径和
    public static int process(int[][] arr, int row, int col) {
        //base case
        //因为在内部限定了走的方法,所以只可能从左上角走到右下角,不会有别的可能
        //最后的情况只有可能走到这里,不会有其他的位置
        if (row == arr.length - 1 && col == arr[0].length - 1) return arr[row][col];

        int cur = arr[row][col];
        if (row == arr.length - 1 && col < arr[0].length) { //在底部,只能向右走
            return cur + process(arr, row, col + 1);
        }
        if (col == arr[0].length - 1 && row < arr.length) { //在最右侧,只能向下走
            return cur + process(arr, row + 1, col);
        }
        //其他位置,返回更小的那一个
        return cur + Math.min(process(arr, row + 1, col), process(arr, row, col + 1));
    }


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
        System.out.println(process(m, 0, 0));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(process(m, 0, 0));
    }

}
