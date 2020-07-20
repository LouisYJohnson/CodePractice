package leetcode.median;

import java.util.ArrayList;

public class LC72Combinations {
    //题目描述
    //给出两个整数n和k，返回从1到n中取k个数字的所有可能的组合
    //例如：
    //如果n=4，k=2，结果为
    //[
    // [2,4],
    // [3,4],
    // [2,3],
    // [1,2],
    // [1,3],
    // [1,4],
    // ]
    public class Solution {
        /**
         *
         * @param n int整型
         * @param k int整型
         * @return int整型ArrayList<ArrayList<>>
         */
        public ArrayList<ArrayList<Integer>> combine (int n, int k) {
            // write code here
            //回溯法结题,并且要的是可能组合,不考虑顺序问题
            if (n < k) return null;
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            ArrayList<Integer> helpList = new ArrayList<>();
            process(n, k, 1, helpList, res);
            return res;
        }

        //n为能够取的最大数,k为剩余的组合个数,start表示从哪个数开始拿
        public void process(int n, int k, int start, ArrayList<Integer> helpList, ArrayList<ArrayList<Integer>> res) {
            //base case
            if (k == 0) {
                res.add(new ArrayList<>(helpList));
                return;
            }

            for (int i = start; i <= n; i++) {
                helpList.add(i);
                process(n, k - 1, i + 1, helpList, res);
                helpList.remove(helpList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC72Combinations().new Solution();
        ArrayList<ArrayList<Integer>> res = solution.combine(2,2);
        System.out.println(1);
    }
}
