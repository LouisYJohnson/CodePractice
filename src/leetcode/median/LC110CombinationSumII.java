package leetcode.median;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LC110CombinationSumII {
    //题目描述
    //给出一组候选数C和一个目标数T，找出候选数中起来和等于T的所有组合。
    //C中的每个数字在一个组合中只能使用一次。
    //注意：
    //题目中所有的数字（包括目标数T）都是正整数
    //组合中的数字 (a 1, a 2, … , a k) 要按非递增排序 (ie, a 1 ≤ a 2 ≤ … ≤ a k).
    //结果中不能包含重复的组合
    //例如：给定的候选数集是[10,1,2,7,6,1,5]，目标数是8
    //解集是：
    //[1, 7]
    //[1, 2, 5]
    //[2, 6]
    //[1, 1, 6]
    //用回溯法做
    public class Solution {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
            //必须将其进行排列
            Arrays.sort(num);
            //辅助列表
            ArrayList<Integer> arrayList = new ArrayList<>();
            process(num, target, 0, arrayList);
            return res;
        }
        public void process(int[] num, int target, int start, ArrayList arrayList) {
            if (target < 0) {
                return;
            } else if (target == 0) {
                res.add(new ArrayList<>(arrayList));
            }else {
                for (int i = start; i < num.length; i++) {
                    //TODO
                    if (i > start && num[i] == num[i - 1]) {    //加入这句就能给res去重(1,1,6与6,1,1去掉),如果不加就是CombinationSumI,重复结果
                        continue;
                    }
                    arrayList.add(num[i]);
                    //如果允许重复用同一个位置的元素的话的话,这里是i
                    process(num, target - num[i], i + 1, arrayList);
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
    }
}
