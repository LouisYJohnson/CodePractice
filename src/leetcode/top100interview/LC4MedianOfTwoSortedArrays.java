package leetcode.top100interview;

public class LC4MedianOfTwoSortedArrays {
    //给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
    //请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    //你可以假设 nums1 和 nums2 不会同时为空。
    //示例 1:
    //nums1 = [1, 3]
    //nums2 = [2]
    //则中位数是 2.0
    //示例 2:
    //nums1 = [1, 2]
    //nums2 = [3, 4]
    //则中位数是 (2 + 3)/2 = 2.5
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            //https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
            int len = nums1.length + nums2.length;
            int left = -1;
            int right = -1;
            int aStart = 0;
            int bStart = 0;
            for (int i = 0; i <= len / 2; i++) {
                left = right;   //总共遍历len/2 + 1次,left存储的数据一直是在right的前一个
                //如果总长度是奇数,那么直接返回right,否则返回二者之和/2
                //注意,这里和求下标的中间位置不一样,下标那个输入值是下标,从0开始
                //这里的len是长度,从1开始
                if (aStart < nums1.length && (bStart >= nums2.length || nums1[aStart] < nums2[bStart])) {
                    right = nums1[aStart++];
                } else {
                    right = nums2[bStart++];
                }
            }
            if ((len & 1) == 0) {
                return (left + right) / 2.0;
            }else {
                return right;
            }
        }
    }
}
