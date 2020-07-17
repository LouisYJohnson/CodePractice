package leetcode.median;

import java.util.Map;

public class LC108TrappingRainWater {
    //题目描述
    //给出n个数字，表示一个高程图，高程图中每一条的宽度为1，请计算下雨之后这个地形可以存储多少水
    //例如
    //给出[0,1,0,2,1,0,1,3,2,1,2,1],返回6.

    //预处理数组,分别找到每个方块左边最高方块与右边最高方块
    //然后计算每个方块的装水量
    public class Solution {
        /**
         *
         * @param A int整型一维数组
         * @return int整型
         */
        public int trap (int[] A) {
            // write code here
            if (A == null || A.length < 3) {
                return 0;
            }
            int[] helpLeftMax = new int[A.length];
            helpLeftMax[0] = A[0];
            int[] helpRightMax = new int[A.length];
            helpRightMax[A.length - 1] = A[A.length - 1];
            //leftMax中装着的是每个数组左边的最大值(包括自身)
            //rightMax中装着的是每个数组右边的最大值(包括自身)
            for (int i = 1; i < helpLeftMax.length; i++) {
                if (A[i] < helpLeftMax[i - 1]) {
                    helpLeftMax[i] = helpLeftMax[i - 1];
                }else {
                    helpLeftMax[i] = A[i];
                }
            }
            for (int i = A.length - 2; i >= 0; i--) {
                if (A[i] < helpRightMax[i + 1]) {
                    helpRightMax[i] = helpRightMax[i + 1];
                }else {
                    helpRightMax[i] = A[i];
                }
            }
            int res = 0;
            //两个边不能存水,所以不算
            //最后当前块水的多少取决于左右两个边更低的那一个
            for (int i = 1; i < A.length - 1; i++) {
                int lower = Math.min(helpLeftMax[i], helpRightMax[i]);
                res += Math.max(0, lower - A[i]);
            }
            return res;
        }
    }
}
