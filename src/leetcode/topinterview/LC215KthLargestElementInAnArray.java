package leetcode.topinterview;

public class LC215KthLargestElementInAnArray {
    //在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    //示例 1:
    //输入: [3,2,1,5,6,4] 和 k = 2
    //输出: 5
    //示例 2:
    //输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
    //输出: 4
    //说明:
    //你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            //堆排序输出前k,大根堆
            if (nums == null || nums.length < k) {
                return 0;
            }

            int heapSize = nums.length;
            //先用heapInsert将堆构建好
            for (int i = 0; i < heapSize; i++) {
                heapInsert(nums, i);
            }
            //再每次弹出一个元素(头节点与最后一个节点交换,然后heapSize--),然后heapify
            //弹到k个为止(弹出k-1个,则第k个就是要的结果)
            for (int i = 0; i < k - 1; i++) {
                swap(nums, 0, heapSize - 1);
                heapSize--;
                heapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        //向上
        //大根堆,如果父节点比子节点小,则父节点与当前节点进行交换,否则break
        public void heapInsert(int[] nums, int i) {
            while (i >= 0) {
                if (nums[i] > nums[(i - 1) / 2]) {
                    swap(nums, i, (i - 1) / 2);
                    i = (i - 1) / 2;
                } else {
                    break;
                }
            }
        }

        //向下
        //大根堆,如果小于左子或者小于右子,和左子与右子中更大的那个换
        public void heapify(int[] nums, int i, int heapSize) {
            while (i < heapSize) {
                int leftIndex = i * 2 + 1;
                int rightIndex = leftIndex + 1;
                //分为左子和右子都在范围内,只有左子在范围内,和左右子都不在范围内
                //左子右子都在范围内
                if (rightIndex < heapSize) {
                    //得到左右最大元素索引
                    int maxLeftRightIndex = Math.max(nums[leftIndex], nums[rightIndex]) == nums[leftIndex] ? leftIndex : rightIndex;
                    if (nums[i] < nums[maxLeftRightIndex]) {   //说明得换,具体换哪个,看左和右哪个更大
                        swap(nums, i, maxLeftRightIndex);
                        i = maxLeftRightIndex;
                    } else { //否则,不用换,直接break
                        break;
                    }
                } else if (leftIndex < heapSize) {  //只有左子在范围内
                    if (nums[i] == Math.max(nums[i], nums[leftIndex])) {    //不需要换
                        break;
                    }else {     //需要换
                        swap(nums, i, leftIndex);
                        i = leftIndex;
                    }
                }else {     //左右子都不再范围内,直接break
                    break;
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
