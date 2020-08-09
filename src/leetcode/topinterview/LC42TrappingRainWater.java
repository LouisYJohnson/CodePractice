package leetcode.topinterview;

public class LC42TrappingRainWater {
    //给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    //上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
    //示例:
    //输入: [0,1,0,2,1,0,1,3,2,1,2,1]
    //输出: 6
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/trapping-rain-water
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int trap(int[] height) {
            //思路:
            //  不要局限于找波峰波谷,要看每个位置能装多少水
            //装水的多少就是左边的最大值于右边最大值中更小的那个减去自身
            //构建两个辅助数组,分别存储着左边的最大值与右边的最大值
            if (height == null || height.length < 3) {  //1,2个边都没办法存水
                return 0;
            }

            int[] leftMax = new int[height.length];
            int[] rightMax = new int[height.length];
            int res = 0;

            //初始化leftMax数组
            leftMax[0] = height[0];
            for (int i = 1; i < leftMax.length; i++) {
                if (height[i] > leftMax[i - 1]) {
                    leftMax[i] = height[i];
                }else {
                    leftMax[i] = leftMax[i - 1];
                }
            }

            //初始化rightMax数组
            rightMax[rightMax.length - 1] = height[rightMax.length - 1];
            for (int i = rightMax.length - 2; i >= 0; i--) {
                if (height[i] > rightMax[i + 1]) {
                    rightMax[i] = height[i];
                }else {
                    rightMax[i] = rightMax[i + 1];
                }
            }
            //开始计算,两个边缘是没有办法存水的,所以从中间开始走
            for (int i = 1; i < height.length - 1; i++) {
                int helpLeftMax = leftMax[i];
                int helpRightMax = rightMax[i];
                res += Math.min(helpLeftMax, helpRightMax) - height[i];
            }
            return res;
        }
    }
}
