package leetcode.median;

import java.util.ArrayList;

public class LC104Permutations {
    //题目描述
    //给出一组数字，返回该组数字的所有排列
    //例如：
    //[1,2,3]的所有排列如下
    //[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], [3,2,1].
    //回溯法
    public class Solution {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        public ArrayList<ArrayList<Integer>> permute(int[] num) {

            ArrayList<Integer> helpList = new ArrayList<>();
            process(num, 0, helpList);
            return res;
        }

        //递归函数功能:
        //  输入当前是第几个数,当前数之前的都已经排完了(不包括当前数),
        //  存在helpList中,后面的数还没有排
        public void process(int[] num, int i, ArrayList<Integer> helpList) {
            //base case
            if (i == num.length) {
                res.add(new ArrayList<>(helpList));
                return;
            }
            for (int j = 0; j < num.length; j++) {
                if (!helpList.contains(num[j])) {
                    helpList.add(num[j]);
                    process(num, i + 1, helpList);
                    helpList.remove(helpList.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC104Permutations().new Solution();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list = solution.permute(new int[]{0, 1});
        System.out.println(1);
    }

}
