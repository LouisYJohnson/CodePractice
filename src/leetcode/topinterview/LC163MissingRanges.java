package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;

public class LC163MissingRanges {
    //给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
    //
    //示例：
    //
    //输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
    //输出: ["2", "4->49", "51->74", "76->99"]
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/missing-ranges
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/missing-ranges/solution/missing-ranges-shuang-zhi-zhen-fa-by-jyd/
        public List<String> findMissingRanges(int[] nums, int lower, int upper) {
            ArrayList<String> res = new ArrayList<>();
            if (nums == null) {
                return res;
            }
            //核心思想:
            //  从头开始遍历数组,将其两两配对
            //差值分为两种情况:
            //  1.如果二者之间之差了2,说明区间与区间之间只有一个数的差距,只放这个数
            //  2.如果二者之间的差距大于等于2,说明区间与区间之间有两个甚至以上的差距,此时要放的就是一个范围了,需要加上"->"
            long pre = (long) (lower) - 1;  //初始位置的选取非常重要,这里相当于自己造了一个左边界,并且这么写也可以防止nums为空
            //pre这个位置是确实存在的,long必须套在lower外面
            //如果这么写:(long) (lower - 1),如果lower==Integer.MIN_VALUE,内部越界,变为integer.MAX_VALUE
            for (int i = 0; i < nums.length; i++) {
                //如果只差了一个数,说明区间没有断开,没有必要判断
                if (nums[i] - pre == 2) {   //差了两个数,说明区间断开,并且间隙之间只有一个数
                    res.add(String.valueOf(pre + 1));
                } else if (nums[i] - pre > 2) { //差了两个数还多,说明区间断开,并且间隙中间是一个区间
                    res.add(String.valueOf(pre + 1) + "->" + String.valueOf(nums[i] - 1));
                }
                pre = nums[i];
            }
            //到了最后,还有upper没有判断
            if (upper - pre >= 2) {
                res.add(String.valueOf(pre + 1) + "->" + String.valueOf(upper));
            } else if (upper - pre == 1) {
                res.add(String.valueOf(upper));
            }
            return res;
        }
    }
}
