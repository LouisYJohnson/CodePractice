package exams.shangtang;

import java.util.Arrays;
import java.util.Comparator;

public class Main2 {
    public class Solution {
        /**
         *
         * @param intervals int整型二维数组
         * @return int整型
         */
        public int eraseOverlapIntervals (int[][] intervals) {
            // write code here
            if (intervals == null || intervals.length == 0) {
                return 0;
            }

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            int count = 1;
            int end = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                if (end <= intervals[i][0]) {
                    end = intervals[i][1];
                    count++;
                }

            }
            return (intervals.length - count);
        }
    }
}
