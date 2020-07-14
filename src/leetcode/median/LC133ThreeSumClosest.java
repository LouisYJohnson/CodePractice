package leetcode.median;

import java.util.Arrays;

public class LC133ThreeSumClosest {
    //题目描述
    //给出含有n个整数的数组s，
    // 找出s中和加起来的和最接近给定的目标值的三个整数。
    // 返回这三个整数的和。你可以假设每个输入都只有唯一解。
    //   例如，给定的整数 S = {-1 2 1 -4}, 目标值 = 1
    //   最接近目标值的和为 2. (-1 + 2 + 1 = 2).

    public class Solution {
        /**
         * 首先将数组排序
         * 使用三个指针,left,mid,right分别指向对应的三个数
         * 先将这三个数进行相加,
         *  如果和小于target将left++或者mid++(当left+1=mid时)
         *  如果和大于target将right--或者mid--(当right-1=mid时)
         *  如果相等,直接返回这三个指针对应的和即可
         *  直到left或者right与mid重合了就停止
         * @param num int整型一维数组
         * @param target int整型
         * @return int整型
         */
        public int threeSumClosest (int[] num, int target) {
            // write code here
            if (num == null || num.length < 3 ) return 0;
            //对数组进行排序
            Arrays.sort(num);
            int left = 0;
            int right = num.length - 1;
            int mid = left + (right - left) / 2;
            int sum = 0;

            while (left < mid && right > mid) {
                sum = num[left] + num[mid] + num[right];
                if (sum == target) {
                    return sum;
                }else if (sum < target) {
                    if (left + 1 < mid) {
                        left++;
                    }else {
                        mid++;
                    }
                }else {
                    if (right - 1 > mid) {
                        right--;
                    }else {
                        mid--;
                    }
                }
            }
            return sum;
        }


    }
}
