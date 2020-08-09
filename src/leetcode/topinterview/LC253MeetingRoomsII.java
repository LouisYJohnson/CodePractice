package leetcode.topinterview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC253MeetingRoomsII {
    //给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间
    // [[s1,e1],[s2,e2],...] (si < ei)，
    // 为避免会议冲突，同时要考虑充分利用会议室资源，
    // 请你计算至少需要多少间会议室，才能满足这些会议安排。
    //示例 1:
    //输入: [[0, 30],[5, 10],[15, 20]]
    //输出: 2
    //示例 2:
    //输入: [[7,10],[2,4]]
    //输出: 1
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/meeting-rooms-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int minMeetingRooms(int[][] intervals) {
            //使用优先级队列来解决此问题
            //首先,会议按照开始时间排序
            //构造一个小根堆,放着每个房间的结束时间,每次来一个会议,将这个会议与堆顶元素比较
            //如果这个会议开始时间比堆顶元素的结束时间还小那么就要再开一个房间才行
            //否则就将堆顶元素的结束时间改为这个入堆会议的结束时间,不用开新房间
            //最后优先级队列的size就是需要的房间数
            if (intervals == null || intervals.length == 0) return 0;   //没有会议就不需要房间
            PriorityQueue<Integer> rooms = new PriorityQueue<>(intervals.length, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            //先将第一个会议放进去,至少要开一个房间的
            //这个堆里面维持的都是会议结束的时间,想入堆的话,都是看入堆会议的开始时间和所有房间中最先能空出来的比
            rooms.add(intervals[0][1]);
            for (int i = 1; i < intervals.length; i++) {
                if (rooms.peek() <= intervals[i][0]) {
                    rooms.poll();
                    rooms.add(intervals[i][1]);
                }else {
                    rooms.add(intervals[i][1]);
                }
            }
            return rooms.size();
        }
    }
}
