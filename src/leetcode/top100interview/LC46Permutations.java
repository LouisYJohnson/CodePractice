package leetcode.top100interview;

import java.util.ArrayList;
import java.util.List;

public class LC46Permutations {
    //给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    //示例:
    //输入: [1,2,3]
    //输出:
    //[
    //  [1,2,3],
    //  [1,3,2],
    //  [2,1,3],
    //  [2,3,1],
    //  [3,1,2],
    //  [3,2,1]
    //]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/permutations
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            //回溯法
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> helpList = new ArrayList<>();
            boolean[] isUsed = new boolean[nums.length];
            process(nums, 0, helpList, res, isUsed);
            return res;
        }

        public void process(int[] nums, int i, List<Integer> helpList, List<List<Integer>> res, boolean[] isUsed) {
            //base case
            if (i == nums.length) {
                res.add(new ArrayList<>(helpList));
            }

            for (int j = 0; j < nums.length; j++) {
                //相同的位置不能重复使用,所以引入isUsed来判断这个位置的元素是否被用过
                if (isUsed[j]) {
                    continue;
                }
                helpList.add(nums[j]);
                isUsed[j] = true;
                process(nums, i + 1, helpList, res, isUsed);
                helpList.remove(helpList.size() - 1);
                isUsed[j] = false;
            }
        }
    }
}
