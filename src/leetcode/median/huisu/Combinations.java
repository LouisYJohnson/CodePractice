package leetcode.median.huisu;

import java.util.ArrayList;
import java.util.List;

//给你两个整数 n和k，从1-n中选择k个数字的组合。比如n=4，那么从1,2,3,4中选取两个数字的组合，包括图上所述的四种。
//[
//
// [2,4],
//
// [3,4],
//
// [2,3],
//
// [1,2],
//
// [1,3],
//
// [1,4],
//
//]
public class Combinations {
//https://blog.csdn.net/versencoder/article/details/52071930
    public class Solution {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        public List<List<Integer>> combine(int n, int k) {
            List<Integer> list = new ArrayList<Integer>();
            backtracking(n, k, 1, list);
            return result;
        }

        //递归函数功能:
        //输入从哪一个数开始,开始的那个数是第几个数(1算第一个而不是0),总共有几个数
        //以及之前的数排列后的结果list
        //将符合条件的排列结果放入res中
        //实质是以每个数作为开头,后面的数选k-1个
        public void backtracking(int n, int k, int start, List<Integer> list) {
            if (k < 0) return;
            else if (k == 0) {
                result.add(new ArrayList(list));
            } else {
                for (int i = start; i <= n; i++) {
                    list.add(i);
                    backtracking(n, k - 1, i + 1, list);
                    //从i到最后一个数依次放数,所以在放了之后要拿出来
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        solution.combine(4, 2);

        System.out.println(1);
    }

}
