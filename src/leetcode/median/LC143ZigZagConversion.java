package leetcode.median;

import java.util.LinkedList;

public class LC143ZigZagConversion {
    //题目描述
    //字符串"PAYPALISHIRING"写成3行的Z字形的样式如下：
    //
    //P   A   H   N↵A P L S I I G↵Y   I   R
    //
    //按行读这个Z字形图案应该是 "PAHNAPLSIIGYIR"
    //
    //请编写代码完成将字符串转化为指定行数的Z字形字符串：
    //
    //string convert(string text, int nRows);
    //
    //convert("PAYPALISHIRING", 3)应该返回"PAHNAPLSIIGYIR"
    //
    //The string"PAYPALISHIRING"is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
    //P   A   H   N↵A P L S I I G↵Y   I   R↵
    //And then read line by line:"PAHNAPLSIIGYIR"
    //Write the code that will take a string and make this conversion given a number of rows:
    //
    //string convert(string text, int nRows);
    //convert("PAYPALISHIRING", 3)should return"PAHNAPLSIIGYIR"

    public static class Solution {
        /**
         * 这个是Z字形来走的
         * 1           9              17
         * 2        8  10         16  18
         * 3     7     11      15     19
         * 4  6        12  14         20
         * 5           13
         *
         * @param s     string字符串
         * @param nRows int整型
         * @return string字符串
         */
        public static String convert(String s, int nRows) {
            // write code here
            if (s == null || s.length() == 0 || nRows <= 1) {
                return s;
            }
            //坐标之间的规律:
            //第一行:1与9之间的距离为2*nRows-2
            //第二行:2与8之间的距离是2*nRows-2-2*1
            //第三行:3与7之间的距离是2*nRows-2-2*2...
            char[] s2Chars = s.toCharArray();
            StringBuffer result = new StringBuffer();
            int dist = 2 * nRows - 2;
            int tempIndex = 0;
            int tempDist1 = 0;
            int tempDist2 = 0;
            //flag用于控制是左边的距离还是右边的距离,
            // 一定要在while循环完将flag置为0即下一行来的时候打印左边距离
            int flag = 0;

            for (int i = 0; i < nRows; i++) {
                //每次tempIndex都被设置为每行的第一个元素所在位置
                tempIndex = i;
                if (i != 0 && i != nRows - 1) { //除去第一行和最后一行的其他行的处理方法
                    tempDist1 = dist - 2 * i;   //左边的距离
                    tempDist2 = dist - tempDist1;   //右边的距离
                    while (tempIndex < s.length()) {
                        result.append(s2Chars[tempIndex]);
                        if (flag == 0) {
                            tempIndex += tempDist1;
                            flag = 1;
                        } else if (flag == 1) {
                            tempIndex += tempDist2;
                            flag = 0;
                        }
                    }
                    //一定要在while循环完将flag置为0即下一行来的时候打印左边距离
                    flag = 0;
                } else { //如果位于第1行或最后一行
                    while (tempIndex < s.length()) {
                        result.append(s2Chars[tempIndex]);
                        tempIndex += dist;
                    }
                }
            }
            return result.toString();
        }

    }

    public static void main(String[] args) {
        System.out.println(Solution.convert("hinmicwsqhptvaprhlmdnjewwpvidxcmfpyqtxklebfzdwskhgnwrtvnksvorzczrbrmybyeeffhdarmggiaafnkxlapkdodgfqgiommvrtytmkauuauaphzajoloeoujgarwmfrgarzmdbjydfatmztyqgmuxjedlxcaftgflhuqldooiqjxqfvinjcksgqeguglnosavorgrhxcaizsnwabfcnalfgrzmepaypxniegsdisljkzhkcpmprxxxqwjwllxdiklosdrdxfohgwringzefwbytmwgxtjhdxwycpbawphcnbmajmeokhoftlmsexakuyixplxmagoojdospvjbcxhwivqpsqbpqjogwnswtimdlbxcwgeaenwoknde",40));
    }
}
