package exams.wubatongcheng;

public class test3 {
    public class Solution {
        /**
         * 翻译组合数
         * @param num int整型 数字加密报文
         * @return int整型
         */
        public int translateNum (int num) {
            // write code here
            String num2Str = String.valueOf(num);
            int[] solveSpace = new int[num2Str.length() + 1];
            solveSpace[0] = 1;
            solveSpace[1] = 1;
            for (int i = 2; i <= num2Str.length(); i++) {
                String temp = num2Str.substring(i - 2, i);
                if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                    solveSpace[i] = solveSpace[i - 1] + solveSpace[i - 2];
                }else {
                    solveSpace[i] = solveSpace[i - 1];
                }
            }
            return solveSpace[solveSpace.length - 1];
        }
    }
}
