package exams.zuiyou;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * func getTriggerTime(increase [][]int, requirements [][]int) []int
     *
     * @param increase     int整型二维数组 属性增加值
     * @param requirements int整型二维数组 剧情触发要求
     * @return int整型一维数组
     */
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        // write code here
        int[][] data = new int[3][increase.length];
        int[] res = new int[requirements.length];
        int index = 0;
        data[0][0] = increase[0][0];
        data[1][0] = increase[0][1];
        data[2][0] = increase[0][2];
        for (int i = 1; i < increase.length; i++) {
            increase[i][0] += increase[i - 1][0];
            increase[i][1] += increase[i - 1][1];
            increase[i][2] += increase[i - 1][2];
            data[0][i] = increase[i][0];
            data[1][i] = increase[i][1];
            data[2][i] = increase[i][2];
        }
        for (int[] requirement : requirements) {
            if (requirement[0] + requirement[1] + requirement[2] == 0) {
                res[index++] = 0;
                continue;
            }
            int i = search(data[0], requirement[0]);
            int j = search(data[1], requirement[1]);
            int k = search(data[2], requirement[2]);
            i++;
            j++;
            k++;
            int t = Math.max(i, Math.max(j, k));
            res[index++] = (t == increase.length + 1 ? -1 : t);
        }
        return res;
    }

    private int search(int[] datum, int i) {
        int start = 0;
        int end = datum.length - 1;
        int mid;

        while (true) {
            if (end == start) {
                if (datum[end] >= i) {
                    return end;
                }else {
                    return end + 1;
                }
            }
            mid = (start + end) / 2;
            if (datum[mid] < i) {
                start = mid + 1;
            }else {
                end = mid;
            }
        }

    }


}
