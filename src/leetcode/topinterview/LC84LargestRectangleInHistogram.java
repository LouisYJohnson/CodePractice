package leetcode.topinterview;

import java.util.Stack;

public class LC84LargestRectangleInHistogram {
    //给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    //求在该柱状图中，能够勾勒出来的矩形的最大面积。
    //以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
    //图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
    //示例:
    //输入: [2,1,5,6,2,3]
    //输出: 10
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Solution {
        //本题使用单调栈做(找到左右距离最近并且第一个比它小的数)
        //因为要找最大矩形的面积,所以这里使用栈中递增的形式
        //栈中存储的是栈的下标
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }

            Stack<Integer> helpStack = new Stack<>();
            int res = 0;

            for (int i = 0; i < heights.length; i++) {
                //栈中保持递增
                if (!helpStack.isEmpty() && heights[helpStack.peek()] > heights[i]) {
                    while (!helpStack.isEmpty() && heights[helpStack.peek()] > heights[i]){
                        int tempIndex = helpStack.pop();
                        int rightSmallIndex = i;
                        //栈没空则弹出数据的在栈中时在它下面的那个坐标为离这个弹出数据最近且比它小的下标
                        int totalLen = helpStack.isEmpty() ? rightSmallIndex : rightSmallIndex - helpStack.peek() - 1;
                        res = Math.max(res, heights[tempIndex] * totalLen);
                    }
                    helpStack.push(i);
                }else {
                    helpStack.push(i);
                }
            }

            //栈有可能没弹完
            while (!helpStack.isEmpty()) {
                int tempIndex = helpStack.pop();
                int totalLen = helpStack.isEmpty() ? heights.length : heights.length - 1 - helpStack.peek();
                res = Math.max(res, heights[tempIndex] * totalLen);
            }
            return res;
        }
    }
}
