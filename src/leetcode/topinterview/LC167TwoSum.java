package leetcode.topinterview;

public class LC167TwoSum {
    //给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
    //
    //函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
    //
    //说明:
    //
    //返回的下标值（index1 和 index2）不是从零开始的。
    //你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
    //示例:
    //
    //输入: numbers = [2, 7, 11, 15], target = 9
    //输出: [1,2]
    //解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

    //双指针解法
    //  因为是有序数组,所以两个指针分别指在左边和右边,如果当前左右指针的和大于target,右指针左移
    //  小于target左指针右移,直到==target为止
    //因为是有序数组,左边只能向右移动变大,右边只能向左移动变小
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] == target) {
                    return new int[] {left + 1, right + 1};
                }else if (numbers[left] + numbers[right] < target) {
                    left++;
                }else {
                    right--;
                }
            }
            return null;
        }
    }



//    class Solution {
//        public int[] twoSum(int[] numbers, int target) {
//            //使用回溯法,时间复杂度太高,oj过不去
//            int[] res = new int[2];
//            ArrayList<Integer> helpList = new ArrayList<>();
//            process(numbers, 0, 2, 0, target, helpList, res);
//            return res;
//        }
//
//        public void process(int[] numbers, int start, int num, int sum, int target, ArrayList<Integer> helpList, int[] res) {
//            //base case
//            if (num == 0 && sum == target) {
//                int index = 0;
//                for (Integer integer : helpList) {
//                    res[index++] = integer + 1;
//                }
//                return;
//            }
//
//            for (int i = start; i < numbers.length; i++) {
//                helpList.add(i);
//                process(numbers, i + 1, num - 1, sum + numbers[i], target, helpList, res);
//                helpList.remove(helpList.size() - 1);
//            }
//        }
//    }

    public static void main(String[] args) {
    }

}
