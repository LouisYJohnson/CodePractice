package leetcode.topinterview;

public class LC26RemoveDuplicatesFromSortedArray {
    //给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
    //不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
    //示例 1:
    //给定数组 nums = [1,1,2],
    //函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
    //你不需要考虑数组中超出新长度后面的元素。
    //示例 2:
    //给定 nums = [0,0,1,1,1,2,2,3,3,4],
    //函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
    //你不需要考虑数组中超出新长度后面的元素。
    //说明:
    //为什么返回数值是整数，但输出的答案是数组呢?
    //请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
    //你可以想象内部操作如下:
    //// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
    //int len = removeDuplicates(nums);
    //// 在函数里修改输入数组对于调用者是可见的。
    //// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
    //for (int i = 0; i < len; i++) {
    //    print(nums[i]);
    //}
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int removeDuplicates(int[] nums) {
            //https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-xiang-by-/
            //双指针法
            //一个指针i指向当前元素,另外一个指针j用来去重,如果重复了,就让第二个指针++直到不重复为止
            //此时将右边指针的指复制到i+1的位置,然后i++,这样时刻保持与右边指针指向元素相同
            //直到j越界位置
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] != nums[j]) {
                    nums[++i] = nums[j];
                }
            }
            //i是不重复子数组的最后一个元素的下标,所以长度要+1
            return i + 1;
        }
    }
}
