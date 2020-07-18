package leetcode.median;

import java.util.ArrayList;
import java.util.Arrays;

public class LC103PermutationsII {
    //题目描述
    //给出一组可能包含重复项的数字，返回该组数字的所有排列
    //例如；
    //[1,1,2]的排列如下：
    //[1,1,2],[1,2,1], [2,1,1].

    //仍然是回溯法,加入一个boolean数组来判断位于i位置上的元素是否被使用过
    //而且要在数组进入排列前,将数组进行排序
    public class Solution {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
            Arrays.sort(num);
            boolean[] helps = new boolean[num.length];
            ArrayList<Integer> helpList = new ArrayList<>();

            process(num, 0, helps, helpList);
            return res;
        }

        //这里的i表示深度
        public void process(int[] num, int i, boolean[] helps, ArrayList<Integer> helpList) {
            //base case
            if (i == num.length) {
                res.add(new ArrayList<Integer>(helpList));
                return;
            }

            for (int j = 0; j < num.length; j++) {
                //同一个位置不能重复加
                if (helps[j]) {
                    continue;
                }
                //当前拿到的数如果和自己上一个位置的数相等,那这个数就不要了,因为112,在第2个1的位置
                //如果去拿前面的那个1,得到的仍然是112,不符合结果
                if (j > 0 && num[j] == num[j - 1] && !helps[j - 1]) {
                    continue;
                }
                helpList.add(num[j]);
                helps[j] = true;
                process(num, i + 1, helps, helpList);
                helpList.remove(helpList.size() - 1);
                helps[j] = false;
             }

        }
    }
}
