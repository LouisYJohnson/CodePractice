package leetcode.median.huisu;

import leetcode.median.LC103PermutationsII;
import leetcode.median.LC104Permutations;
import leetcode.median.LC110CombinationSumII;

import java.util.ArrayList;

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
    /**
     * {@link LC104Permutations}
     * {@link LC103PermutationsII}
     * {@link LC110CombinationSumII}
     */
    //套路网址:
    //https://blog.csdn.net/versencoder/article/details/52071930
    //这个套路的效果就是从一个数组中取到所有下标上的组合或排列(深度优先遍历,是个树状结构),如果不传入限制,则是数组中所有的数的组合
    //如果在base case中写入限制一个组合中最多几个数,则能返回所有的有规定个数的所有组合
    //如果在for循环中的起始设置为0,那就是当前点还会去找前面的点作为结果(排列),如果起始为当前点坐标,则不会去前面找点(组合)

    //回溯法的本质是dfs,即深度优先遍历
    //感觉很多人都没说明，回溯思想的核心，就是dfs,也就是helper中的for循环，每次都有nums.
    //nums.length次选择，但是这时候脑海中有一张树状图的网，唯一不同的是很多时候
    //回溯会剪枝，比如used的设置，这样时间复杂度又n^n变为n!。
    public class Solution {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        public ArrayList<ArrayList<Integer>> combine(int n, int k) {
            ArrayList<Integer> helpList = new ArrayList<Integer>();
            backtracking(n, k, 1, helpList);
            return result;
        }

        //递归函数功能:
        //输入从哪一个数开始,开始的那个数是第几个数(1算第一个而不是0),总共有几个数
        //以及之前的数排列后的结果ArrayList
        //将符合条件的排列结果放入res中
        //实质是以每个数作为开头,后面的数选k-1个
        //这里的start表示层数,也表示第一个数是1,可以写成2个,从0层开始,目前数字为1,总共的层数为k
        public void backtracking(int n, int k, int start, ArrayList<Integer> helpList) {
            //k就是剩余组合的个数
            if (k < 0) {
                return;
            }
            if (k == 0) {
                result.add(new ArrayList(helpList));
                return;
            }
            //从start开始表示以每个位置上的数开始,向后找组合
            for (int i = start; i <= n; i++) {
                helpList.add(i);
                backtracking(n, k - 1, i + 1, helpList);
                //从i到最后一个数依次放数,所以在放了之后要拿出来
                helpList.remove(helpList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
        solution.combine(4, 2);

        System.out.println(1);
    }

}
