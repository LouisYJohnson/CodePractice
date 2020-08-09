package leetcode.topinterview;

import myCodePractice.zuo.advance.class06.SubArrayMaxProduct;

import java.util.Arrays;

public class LC152MaximumProductSubarray {
    //给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    //示例 1:
    //输入: [2,3,-2,4]
    //输出: 6
    //解释: 子数组 [2,3] 有最大乘积 6。
    //示例 2:
    //输入: [-2,0,-1]
    //输出: 0
    //解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/maximum-product-subarray
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * {@link SubArrayMaxProduct}
     */
    class Solution {
        public int maxProduct(int[] nums) {
            //每一个位置都要与这个位置之前的得到的最大,最小乘积相乘,以及只要当前位置中的最大作为当前的最大值
            //并将最小值保留起来
            //这个数组的含义就是以这个位置结尾的最大/最小乘积
            //所以总共要维护两个数组
            int[] helpMin = new int[nums.length];
            int[] helpMax = new int[nums.length];
            helpMax[0] = nums[0];
            helpMin[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                helpMax[i] = Math.max(nums[i], Math.max(nums[i] * helpMax[i - 1], nums[i] * helpMin[i - 1]));
                helpMin[i] = Math.min(nums[i], Math.min(nums[i] * helpMax[i - 1], nums[i] * helpMin[i - 1]));
            }
            Arrays.sort(helpMax);
            return helpMax[helpMax.length - 1];
        }
    }
}
