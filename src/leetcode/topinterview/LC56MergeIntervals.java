package leetcode.topinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LC56MergeIntervals {
    //给出一个区间的集合，请合并所有重叠的区间。
    //示例 1:
    //输入: [[1,3],[2,6],[8,10],[15,18]]
    //输出: [[1,6],[8,10],[15,18]]
    //解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    //示例 2:
    //输入: [[1,4],[4,5]]
    //输出: [[1,5]]
    //解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/merge-intervals
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0) return intervals;
            //我们用数组 merged 存储最终的答案。
            //首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，
            // 并按顺序依次考虑之后的每个区间：
            //如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，
            // 我们可以直接将这个区间加入数组 merged 的末尾；
            //否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];   //按照一维数组的第一个元素升序排序
                }
            });
            ArrayList<int[]> helpList = new ArrayList<>();
            helpList.add(intervals[0]);
            for (int i = 1; i < intervals.length; i++) {
                //如果当前位置的左边界和上一个区间的右边界相交了
                if (intervals[i][0] <= helpList.get(helpList.size() - 1)[1]) {
                    //如果右边界小于上一个区间的右边界不用扩充上一个位置的区间了,直接被合并
                    if (intervals[i][1] <= helpList.get(helpList.size() - 1)[1]) {
                        continue;
                    } else {
                        //如果右边界大于上一个区间的右边界,则需要扩充当前最右边界
                        helpList.get(helpList.size() - 1)[1] = intervals[i][1];
                    }
                } else {    //如果左边界和上一个区间的右边界没有交点,则新增一个区间
                    helpList.add(intervals[i]);
                }
            }
            int[][] res = new int[helpList.size()][2];
            for (int i = 0; i < helpList.size(); i++) {
                res[i] = helpList.get(i);
            }
            return res;
        }
    }
}
