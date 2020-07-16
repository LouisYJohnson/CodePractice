package leetcode.median;

import java.util.HashSet;

public class LC114ValidSudoku {
    //数独由9×9的格子组成，
    //
    //规则是：每行、列、宫各自都要填上1-9的数字，要做到每行、列、宫里的数字都不重复。
    //
    //宫是由3×3的小格子组成的
    //题目描述
    //根据数独的规则Sudoku Puzzles - The Rules.判断给出的局面是不是一个符合规则的数独局面
    //数独盘面可以被部分填写，空的位置用字符'.'.表示
    public class Solution {
        public boolean isValidSudoku(char[][] board) {
            //思路:
            //  使用HashSet的add方法判断是否重复
            //本题的关键在于如何将坐标转换为九宫格中的坐标
            for (int i = 0; i < 9; i++) {
                //每个大循环对应一行,一列,一个九宫格
                HashSet<Character> row = new HashSet<>();
                HashSet<Character> col = new HashSet<>();
                HashSet<Character> cube = new HashSet<>();
                for (int j = 0; j < 9; j++) {
                    //第i行
                    if (board[i][j] != '.' && !row.add(board[i][j])) {
                        //if中条件成立,当前位置不为空或已经重复
                        return false;
                    }
                    //第i列
                    if (board[j][i] != '.' && !col.add(board[j][i])) {
                        return false;
                    }
                    //第i个cube
                    //外部i表示第i个cube,j表示cube中的第1到9个数
                    //所以用这两个数转化成对应的cube中的元素下标
                    //cube内部变化:行:j/3,列:j%3,在此基础上加上cube的起始位置,才是每个cube坐标的真正位置
                    //基础位置:列为(i%3) * 3, 行为(i / 3) * 3
                    //拿张纸画一下就出来了
                    int cubeRow = (i / 3) * 3 + j / 3;
                    int cubeCol = (i % 3) * 3 + j % 3;
                    if (board[cubeRow][cubeCol] != '.' && !cube.add(board[cubeRow][cubeCol])) {
                        return false;
                    }
                }
            }
            return true;

        }
    }
}
