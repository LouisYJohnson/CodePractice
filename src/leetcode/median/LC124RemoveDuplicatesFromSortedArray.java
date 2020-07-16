package leetcode.median;

import java.time.chrono.MinguoDate;

public class LC124RemoveDuplicatesFromSortedArray {
    //题目描述
//    删除排序数组中的重复项
//    给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
//    不要使用额外的数组空间，
//
//    你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
//    示例 1:
//    给定数组 nums = [1,1,2],
//    函数应该返回新的长度 2,
//    并且原数组 nums
//    的前两个元素被修改为 1,2。
//    你不需要考虑数组中超出新长度后面的元素。
//    示例 2:
//    给定 nums = [0,0,1,1,1,2,2,3,3,4],
//    函数应该返回新的长度 5,
//    并且原数组 nums
//    的前五个元素被修改为 0,1,2,3,4。
//    你不需要考虑数组中超出新长度后面的元素。

    //本质:收集不相同的数
    public class Solution {
        public int removeDuplicates(int[] A) {
            if (A == null || A.length == 0) return 0;

            int i = 0;
            int j = 0;
            //i如果和j相同,i就不动弹
            //如果不相同,本质就是将i位置+1的数换作j位置上的数
            //就是每次将最新的数都放到i这里来
            //而且最后要的数组的大小,其实就是放着不相同数的i数组的大小
            while (++j < A.length) {
                if (A[i] != A[j]) {
                    A[++i] = A[j];
                }
            }
            return ++i; //i表示下标,所以要+1才能返回
        }
    }



    //下面的方法只是找出数组长度,并没有改变数组
//    public class Solution {
//        public int removeDuplicates(int[] A) {
//            if (A == null || A.length == 0) return 0;
//
//            int res = 0;
//            for (int i = 0; i < A.length; i++) {
//                int index = i + 1;
//                while (index < A.length && A[i] == A[index]) {
//                    index++;
//                }
//                //因为i进入下一次循环的时候还要++,所以在这里先-1
//                i = index - 1;
//                res++;
//                if (index == A.length) {
//                    return res;
//                }
//            }
//            return res;
//        }
//    }
//
//    public static void main(String[] args) {
//        LC124RemoveDuplicatesFromSortedArray.Solution solution = new LC124RemoveDuplicatesFromSortedArray().new Solution();
//        int[] ar = new int[] {1,1,1,1,2,2,3,3,4,4};
//        System.out.println(solution.removeDuplicates(ar));
//    }
}


