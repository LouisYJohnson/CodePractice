package leetcode.topinterview;

public class LC238ProductOfArrayExceptSelf {
    //给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
    //示例:
    //输入: [1,2,3,4]
    //输出: [24,12,8,6]
    //提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
    //说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
    //进阶：
    //你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/product-of-array-except-self
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            //创建两个辅助数组,大小和nums相同,每个位置分别存储不包含这个位置的左边,右边的乘积
            int[] helpLeft = new int[nums.length];
            int[] helpRight = new int[nums.length];

            helpLeft[0] = 1;
            int help = 1;
            for (int i = 1; i < helpLeft.length; i++) {
                //必须使用nums中的值而不是helpLeft中的值
                //会造成结果数组全1的情况
                helpLeft[i] = help * nums[i - 1];
                help = helpLeft[i];
            }
            helpRight[helpRight.length - 1] = 1;
            help = 1;
            for (int i = helpRight.length - 2; i >= 0; i--) {
                helpRight[i] = help * nums[i + 1];
                help = helpRight[i];
            }
            //遍历两个数组,将两个数组相同位置相乘得到的结果存入结果数组中
            int[] res = new int[nums.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = helpLeft[i] * helpRight[i];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC238ProductOfArrayExceptSelf().new Solution();
        System.out.println(solution.productExceptSelf(new int[]{1, 2, 3, 4}));
    }
}
