package leetcode.topinterview;

import java.util.Arrays;

public class LC169MajorityElement {
    //给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
    //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    //示例 1:
    //输入: [3,2,3]
    //输出: 3
    //示例 2:
    //输入: [2,2,1,1,1,2,2]
    //输出: 2
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/majority-element
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int majorityElement(int[] nums) {
            //将数组进行升序排列,那么位于n/2位置的元素,一定是结果
            //在这个数不是最大值或者最小值的情况下,是一定能覆盖到中间这个索引的
            //而在该数为所在数组的最大值或者最小值的时候,不管是奇数还是偶数,都会重叠
            //比如长度为7,则在下标为3处重叠,长度为6,在下标为3处重叠,所以这个位置为长度/2
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }
}
