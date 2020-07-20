package leetcode.median;

import org.omg.CORBA.MARSHAL;

public class LC77EditDistance {
    //题目描述
    //给定两个单词word1和word2，请计算将word1转换为word2至少需要多少步操作。
    //你可以对一个单词执行以下3种操作：
    //a）在单词中插入一个字符
    //b）删除单词中的一个字符
    //c）替换单词中的一个字符
    public class Solution {
        /**
         * @param word1 string字符串
         * @param word2 string字符串
         * @return int整型
         */
        public int minDistance(String word1, String word2) {
            // write code here

//            return process(word1, word2, 0, 0);
            return process2(word1, word2);
        }

        //动态规划
        public int process2(String word1, String word2) {
            //变量只有index1和index2,而且index1从0到word1.length(),index2从0到word2.length()
            //所以dp对应一个二维表,行表示index1,列表示index2,行从0到word1.length(),列从0到word2.length()
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            //首先按照base case初始化dp
            dp[word1.length()][word2.length()] = 0;
            for (int col = word2.length() - 1; col >= 0; col--) {   //从length-1开始是因为右下角的点已经填上0了
                dp[word1.length()][col] = word2.length() - col;
            }
            for (int row = word1.length() - 1; row >= 0; row--) {   //从length-1开始是因为右下角的点已经填上0了
                dp[row][word2.length()] = word1.length() - row;
            }
            //其余自由位置,依赖右边的点,下边的点,和右下的点,所以填表顺序为从右到左,从下到上填
            for (int col = word2.length() - 1; col >= 0; col--) {
                for (int row = word1.length() - 1; row >= 0; row--) {
                    if (word1.charAt(row) == word2.charAt(col)) {
                        dp[row][col] = dp[row + 1][col + 1];
                    } else {
                        dp[row][col] = Math.min(dp[row + 1][col + 1],
                                Math.min(dp[row + 1][col],
                                        dp[row][col + 1])) + 1;
                    }
                }
            }
            return dp[0][0];
        }

        //虽然递归oj过了,但是还是来改动态规划来熟练一下动态规划到解空间的映射
        //递归函数功能:
        //  注意,并不用涉及字符串的变化,那样只会让问题复杂化,可以用位置下标来替代这一操作,具体怎么替代,看递归函数体内的写法
        //  输入两个要被转成相同字符串的字符串,与两个字符串起始位置,起始位置之前(不包括起始位置)的两个字符串相同位置都相同
        //  返回从这个起始位置开始(不包括这个起始位置),两个字符串中让word1变为word2最少需要多少次操作
        public int process(String word1, String word2, int index1, int index2) {
            //base case,分为三个情况,分别为,word1和word2同时排好,word1好了word2没好,word1没好word2好了
            if (index1 == word1.length() && index2 == word2.length()) { //如果到了尽头而且之前的位置都已经相同了,直接返回0,不需要操作了
                return 0;
            }
            //下面两个if中为什么用坐标相减:对于两个长度不同但是短的和长的前面相同的情况,最少的操作就是短的直接添加自己没有的字符
            if (index1 == word1.length() && index2 < word2.length()) {  //到了这里,说明word1已经到了尽头,而word2还没有,word1需要补上那些自己没有的字符,补多少,看index2和word2差多少
                return word2.length() - index2; //index2的位置是一个还没有决定如何处理的位置,因为递归函数传入的参数就是不包括当前位置,之前的位置已经排好了
                //所以,真正差的字符个数为(word2.length - 1) - (index2) + 1,简化结果为word2.length() - index2
            }
            if (index2 == word2.length() && index1 < word1.length()) {
                return word1.length() - index1;
            }

            if (word1.charAt(index1) == word2.charAt(index2)) { //如果在该位置两个字符串上的字符相同,不需要进行操作,所以直接进入下一层
                return process(word1, word2, index1 + 1, index2 + 1);
            }
            //如果没进base case,那么分别对word1进行插入字符,删除字符,和替换字符的操作
            return Math.min(process(word1, word2, index1, index2 + 1),    //对word1进行插入字符操作
                    Math.min(process(word1, word2, index1 + 1, index2),    //对word1进行删除字符的操作
                            process(word1, word2, index1 + 1, index2 + 1) //对word1进行字符替换的操作
                    )) + 1; //在所有的返回值中要加上这一层操作数1,因为本层执行了三种操作并选取其中的最小值
        }
    }
}
