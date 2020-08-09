package leetcode.topinterview;

public class LC88MergeSortedArray {
    //给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
    //说明:
    //初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
    //你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
    //示例:
    //输入:
    //nums1 = [1,2,3,0,0,0], m = 3
    //nums2 = [2,5,6],       n = 3
    //输出: [1,2,2,3,5,6]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/merge-sorted-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            //merge sort的过程用来做这道题
            //可以不用O(m+n)的空间复杂度,优化为O(m)
            //做法就是申请一个m长度的数组来放nums1中前m个元素,然后指针指向这个辅助数组,结果直接覆盖到nums1中去
            int helpNums1P = 0;
            int helpNums2P = 0;
            int[] help = new int[m + n];
            int helpIndex = 0;
            while (helpNums1P < m && helpNums2P < n) {
                if (nums1[helpNums1P] < nums2[helpNums2P]) {
                    help[helpIndex++] = nums1[helpNums1P++];
                }else {
                    help[helpIndex++] = nums2[helpNums2P++];
                }
            }
            while (helpNums1P < m) {
                help[helpIndex++] = nums1[helpNums1P++];
            }
            while (helpNums2P < n) {
                help[helpIndex++] = nums2[helpNums2P++];
            }
            for (int i = 0; i < help.length; i++) {
                nums1[i] = help[i];
            }
        }
    }
}
