package leetcode.topinterview;

public class LC283MoveZeroes {
    //给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //
    //示例:
    //
    //输入: [0,1,0,3,12]
    //输出: [1,3,12,0,0]
    //说明:
    //
    //必须在原数组上操作，不能拷贝额外的数组。
    //尽量减少操作次数。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/move-zeroes
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    class Solution {
        public void moveZeroes(int[] nums) {
            //https://leetcode-cn.com/problems/move-zeroes/solution/0-ms-zai-suo-you-java-ti-jiao-zhong-ji-bai-liao--2/
            //遍历数组,将不为0的元素都填到数组前面,填完之后,数组后面全填0
            int index = 0;
            for (int num : nums) {
                if (num != 0) {
                    nums[index++] = num;
                }
            }
            while (index < nums.length) {
                nums[index++] = 0;
            }
        }
    }
}
