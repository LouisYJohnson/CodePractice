package leetcode.median;

public class LC14SingleNumberII {
    //题目描述
    //现在有一个整数类型的数组，数组中只有一个元素只出现一次，其余元素都出现三次。你需要找出只出现一次的元素
    //注意：
    //你需要给出一个线性时间复杂度的算法，你能在不使用额外内存空间的情况下解决这个问题么？
    //
    //Given an array of integers, every element appears three times except for one. Find that single one.
    //Note:
    //Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
    public class Solution {
        /**
         *
         * @param A int整型一维数组
         * @return int整型
         */
        public int singleNumber (int[] A) {
            // write code here
            //链接：https://www.nowcoder.com/questionTerminal/1097ca585245418ea2efd0e8b4d9eb7a
            //来源：牛客网
            //
            ////由于只有一个出现一次的数字，其余都是出现三次，那么有如下思路
            //// 针对于序列中出现三次的所有数字的每一位来说，相加的结果只有两种
            ////1+1+1==3 或者0+0+0=0
            ////那么再加上只出现一次的数字的对应位数字的话，也有两种0或者4
            ////所以我们可以对上述的每一位求和之后对3取余，结果将会是1或者0
            ////也就是代表了出现一次的的这个数字对应位置上是1或者0
            //本质:
            //  转换为三进制运算
            if (A == null || A.length == 0) return -1;

            int result = 0;
            //遍历每一个bit位
            for (int i = 0; i < 32; i++) {
                //在bit位上将所有数的该bit位相加并%3,就是只出现一次的数在该bit位是否有值
                int bit = 0;
                for (int j = 0; j < A.length; j++) {
                    bit += (A[j] >> i) & 1;

                }
                result |= (bit % 3) << i;
            }

            return result;
        }
    }
}
