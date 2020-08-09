package leetcode.topinterview;

public class LC287FindTheDuplicateNumber {
    //给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
    // 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
    //示例 1:
    //输入: [1,3,4,2,2]
    //输出: 2
    //示例 2:
    //输入: [3,1,3,4,2]
    //输出: 3
    //说明：
    //不能更改原数组（假设数组是只读的）。
    //只能使用额外的 O(1) 的空间。
    //时间复杂度小于 O(n2) 。
    //数组中只有一个重复的数字，但它可能不止重复出现一次。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/find-the-duplicate-number
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Solution {
        public int findDuplicate(int[] nums) {
            //使用二分法
            //  注意:这里的二分法并不是说左右坐标,而是说数字
            //因为这里的数字是从1到n的,所以不管数字在数组中的排列是啥样,而是说要注重数字出现在哪个范围
            //二分法的思路是先猜一个数（有效范围 [left, right]里的中间数 mid），
            // 然后统计原始数组中小于等于这个中间数的元素的个数 cnt，如果 cnt 严格大于 mid，
            // （注意我加了着重号的部分「小于等于」、「严格大于」）。
            // 根据抽屉原理，重复元素就在区间 [left, mid] 里；

            int left = 1;
            int right = nums.length - 1;    //长度是n+1,最大数字为n,所以右边的数为nums.length - 1

            while (left < right) {
                //中间数,猜一个,猜中间那个数
                //这道题特殊的地方在于最大数和数组长度是关联的
                int mid = left + (right - left) / 2;
                int count = 0;
                for (int num : nums) {
                    if (num <= mid) {
                        count++;
                    }
                }
                //这道题特殊的地方在于最大数和数组长度是关联的,所以不会出现小于mid的数的个数永远也达不到mid的情况
                if (count > mid) {
                    right = mid;
                } else {
                    left = mid + 1;
                }

            }
            return left;
        }
    }
}
