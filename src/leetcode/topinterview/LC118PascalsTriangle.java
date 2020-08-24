package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;

public class LC118PascalsTriangle {
    //给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
    //在杨辉三角中，每个数是它左上方和右上方的数的和。
    //示例:
    //输入: 5
    //输出:
    //[
    //     [1],
    //    [1,1],
    //   [1,2,1],
    //  [1,3,3,1],
    // [1,4,6,4,1]
    //]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/pascals-triangle
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            List<Integer> secondRow = new ArrayList<>();
            secondRow.add(1);
            secondRow.add(1);
            List<List<Integer>> res = new ArrayList<>();
            if (numRows == 0) {
                return res;
            }
            res.add(firstRow);
            if (numRows == 1) {
                return res;
            }
            res.add(secondRow);
            if (numRows == 2) {
                return res;
            }
            int curRow = 3;
            while (curRow <= numRows) {
                List<Integer> tempRow = new ArrayList<>();

                List<Integer> lastRow = res.get(res.size() - 1);
                for (int i = 0; i < curRow; i++) {
                    if (i == 0 || i == curRow - 1) {
                        tempRow.add(1);
                    } else {
                        tempRow.add(lastRow.get(i) + lastRow.get(i - 1));
                    }
                }
                res.add(tempRow);
                curRow++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC118PascalsTriangle().new Solution();
        solution.generate(5);
    }
}
