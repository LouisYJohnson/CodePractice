package leetcode.topinterview;

import java.util.Arrays;
import java.util.Comparator;

public class LC435NonOverlappingIntervals {
    //给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
    //注意:
    //可以认为区间的终点总是大于它的起点。
    //区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
    //示例 1:
    //输入: [ [1,2], [2,3], [3,4], [1,3] ]
    //输出: 1
    //解释: 移除 [1,3] 后，剩下的区间没有重叠。
    //示例 2:
    //输入: [ [1,2], [1,2], [1,2] ]
    //输出: 2
    //解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
    //示例 3:
    //输入: [ [1,2], [2,3] ]
    //输出: 0
    //解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/non-overlapping-intervals
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            //https://leetcode-cn.com/problems/non-overlapping-intervals/solution/tan-xin-suan-fa-zhi-qu-jian-diao-du-wen-ti-by-labu/
            //贪心法,将这些数组按照end升序排好,然后从左到右遍历,找到最大不重叠区间,然后用总区间数减去最大不重叠区间就是结果
            if (intervals == null || intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            int count = 1;  //最大不重叠区间至少有一个
            int mostRight = intervals[0][1];    //这个区间就是intervals中的第一个元素
            for (int i = 1; i < intervals.length; i++) {
                //每次使用当前区间的左节点和上一个右节点进行比较,看是否重叠
                if (intervals[i][0] >= mostRight) { //不重叠,更新右边界
                    mostRight = intervals[i][1];
                    count++;
                }//重叠就不必管了
            }
            return intervals.length - count;
        }
    }
}
