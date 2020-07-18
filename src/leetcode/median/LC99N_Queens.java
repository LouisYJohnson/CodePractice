package leetcode.median;

import java.util.ArrayList;

public class LC99N_Queens {
    //https://blog.csdn.net/piyongduo3393/article/details/86497081
    //题目描述
    //给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
    //每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 ‘Q’ 和 ‘.’ 分别代表了皇后和空位。
    //示例:
    //输入: 4
    //输出: [
    //[".Q…", // 解法 1
    //“…Q”,
    //“Q…”,
    //“…Q.”],
    //["…Q.", // 解法 2
    //“Q…”,
    //“…Q”,
    //“.Q…”]
    //]
    //解释: 4 皇后问题存在两个不同的解法。
    //皇后不攻击:同行与同列同斜线的皇后会攻击
    //回溯法:
    //  层数对应棋盘的行数

    public class Solution {
        ArrayList<String[]> res = new ArrayList<>();

        public ArrayList<String[]> solveNQueens(int n) {

            //构造一个空棋盘
            char[][] haveQueen = new char[n][n];
            for (int i = 0; i < haveQueen.length; i++) {
                for (int j = 0; j < haveQueen[0].length; j++) {
                    haveQueen[i][j] = '.';
                }
            }
            process(n, 0, haveQueen);

            return res;
        }

        //递归函数功能:
        //  给定棋盘大小nxn,从i行开始(不包括第i行),后面的所有行的皇后放置情况
        //i表示行
        public void process(int n, int i, char[][] haveQueen) {
            //到头了且解法合理,将对应的字符串数组放入结果中
            if (i == n) {
                //temp将棋盘按照行来变成字符串数组
                String[] temp = new String[n];
                for (int j = 0; j < n; j++) {
                    temp[j] = new String(haveQueen[j]);
                }
                res.add(temp);
                return;
            }

            //遍历每一个列
            //同一行中每个列尝试放一个之后,就去掉,让给下一次有可能的列,去掉这一步也是回溯法的关键
            for (int j = 0; j < n; j++) {
                //如果当前位置能够放置皇后的话,才能进入下一层
                //所以每一次放置的皇后一定都是合法的,到base case的时候放心变字符串就好了
                if (isValid(haveQueen, i, j)) {
                    haveQueen[i][j] = 'Q';
                    process(n, i + 1, haveQueen);
                    haveQueen[i][j] = '.';
                }
            }

        }

        public boolean isValid(char[][] haveQueen, int row, int col) {
            //因为皇后是从上至下,从左至右放的,所以当前这个子只需要检测左右与上,还有左上右上即可
            //上
            for (int i = row - 1; i >= 0; i--) {
                if (haveQueen[i][col] == 'Q') {
                    return false;
                }
            }
            //左
            for (int i = col - 1; i >= 0; i--) {
                if (haveQueen[row][i] == 'Q') {
                    return false;
                }
            }
            //左上
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (haveQueen[i][j] == 'Q') {
                    return false;
                }
            }
            //右上
            for (int i = row - 1, j = col + 1; i >= 0 && j < haveQueen[0].length; i--, j++) {
                if (haveQueen[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }

}
