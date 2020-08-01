package oj.qianxin.aug1;

import java.util.ArrayList;
import java.util.List;

public class test2 {
    public class Solution {
        /**
         * 返回亲7数个数
         *
         * @param digit int整型一维数组 组成亲7数的数字数组
         * @return int整型
         */
        public int reletive_7(int[] digit) {
            // write code here
            if (digit == null || digit.length == 0) {
                return 0;
            }
            List<Integer> allNums = new ArrayList<>();
            boolean[] isUsed = new boolean[digit.length];
            process(digit, 0, new StringBuilder(), allNums, isUsed);
            int res = 0;
            for (Integer allNum : allNums) {
                if (allNum % 7 == 0) {
                    res++;
                }
            }
            return res;
        }

        //使用回溯法得到所有的数字组合
        public void process(int[] digit, int i, StringBuilder helpSB, List<Integer> allNums, boolean[] isUsed) {
            //base case
            if (i == digit.length) {
                allNums.add(Integer.valueOf(helpSB.toString()));
                return;
            }


            for (int j = 0; j < digit.length; j++) {
                if (!isUsed[j]) {
                    isUsed[j] = true;
                    helpSB.append(digit[j]);
                    process(digit, i + 1, helpSB, allNums, isUsed);
                    isUsed[j] = false;
                    helpSB.deleteCharAt(helpSB.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new test2().new Solution();
        int[] test = new int[] {1,1,2};
        solution.reletive_7(test);
    }
}
