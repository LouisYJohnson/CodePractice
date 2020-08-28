package leetcode.topinterview;

public class LC189RotateArray {
    //给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    //示例 1:
    //输入: [1,2,3,4,5,6,7] 和 k = 3
    //输出: [5,6,7,1,2,3,4]
    //解释:
    //向右旋转 1 步: [7,1,2,3,4,5,6]
    //向右旋转 2 步: [6,7,1,2,3,4,5]
    //向右旋转 3 步: [5,6,7,1,2,3,4]
    //示例 2:
    //输入: [-1,-100,3,99] 和 k = 2
    //输出: [3,99,-1,-100]
    //解释:
    //向右旋转 1 步: [99,-1,-100,3]
    //向右旋转 2 步: [3,99,-1,-100]
    //说明:
    //尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
    //要求使用空间复杂度为 O(1) 的 原地 算法。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/rotate-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public void rotate(int[] nums, int k) {
            //走到了一圈(步数和数组长度相同,相当于没走一样,不用走,所以要模上数组长度)
            k = k % nums.length;
            if (nums == null || nums.length == 1 || k == 0) {
                return;
            }
            k = k % nums.length;
            //方法1,分别逆序然后整体逆序
            //右边k个,左边就是len-k个
            reverse(nums, 0, nums.length - k - 1);
            reverse(nums, nums.length - k, nums.length - 1);
            reverse(nums, 0, nums.length - 1);
        }

        public void reverse(int[] nums, int i, int j) {
            //给定数组于左边界和右边界,将这边界中的数组翻转
            int mid = i + (j - i) / 2;
            int rightBound = ((j - i + 1) & 1) == 1 ? mid - 1 : mid;
            for (int k = i; k <= rightBound; k++) {
                swap(nums, k, j--);
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
