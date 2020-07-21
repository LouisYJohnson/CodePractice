package leetcode.top100interview;

import java.util.ArrayList;
import java.util.List;

//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//说明：解集不能包含重复的子集。
//示例:
//输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
//链接：https://leetcode-cn.com/problems/subsets

public class LC78SubSet {
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            //回溯算法
            //本质就是回溯算法,只不过要在调用它的主方法中加入一个限制,就是一个组合中能有几个数
            //因为回溯法能够找到给定个数的所有的排列/组合,所以用一个遍历就能够找到所有规定个数的排列/组合
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> helpList = new ArrayList<>();
            //constrain表示限制,限制一个组合中有几个数
            for (int constrain = 0; constrain <= nums.length; constrain++) {
                process(nums, constrain, 0, helpList, res);
            }
            return res;
        }

        public void process(int[] nums, int num, int i, List<Integer> helpList, List<List<Integer>> res) {
            //base case
            if (num == 0) {
                res.add(new ArrayList<>(helpList));
            }

            for (int j = i; j < nums.length; j++) {
                helpList.add(nums[j]);
                process(nums, num - 1, j + 1, helpList, res);
                helpList.remove(helpList.size() - 1);
            }
        }
    }
}
