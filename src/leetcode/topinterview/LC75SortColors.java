package leetcode.topinterview;

import com.newcoder.zuo3.basic.class01.Class01_QuickSort;

public class LC75SortColors {
    //给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    //此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    //注意:
    //不能使用代码库中的排序函数来解决这道题。
    //示例:
    //输入: [2,0,2,1,1,0]
    //输出: [0,0,1,1,2,2]
    //进阶：
    //一个直观的解决方案是使用计数排序的两趟扫描算法。
    //首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
    //你能想出一个仅使用常数空间的一趟扫描算法吗？
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/sort-colors
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public void sortColors(int[] nums) {
            /**
             * {@link Class01_QuickSort}
             */
            //二分法中的partion过程
            //不一样的是,二分法中的patrion过程的target是最右边的数,而这里是固定的1
            //因为partion中left永远指向小于cur指向数字的最后一个,right永远指向大于cur指向数字的第一个,
            //所以此处的起点分别为-1和nums.length
            //在二分法中right指向nums.length-1是因为nums.length-1位置上是target,被占了,
            //结合right的含义发现,nums.length-1位置上的数只用来比较并不参与交换
            //所以要在最后加上一个swap(nums, cur, right)来让最后一个位置上的数放到属于他的位置上去(等于target的区域)
            int left = -1;
            int right = nums.length;
            int cur = 0;
            while (cur < right) {
                if (nums[cur] == 1) {
                    cur++;
                } else if (nums[cur] < 1) {
                    swap(nums, ++left, cur++);
                }else {
                    swap(nums, --right, cur);
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
