package leetcode.median;

import java.util.Arrays;

public class LC119NextPermutation {
    //题目描述
    //实现函数next permutation（下一个排列）：
    // 将排列中的数字重新排列成字典序中的下一个更大的排列。将排列中的数字重新排列成字典序中的下一个更大的排列。
    //如果不存在这样的排列，则将其排列为字典序最小的排列（升序排列）
    //需要使用原地算法来解决这个问题，不能申请额外的内存空间
    //下面有机组样例，左边是输入的数据，右边是输出的答案
    //1,2,3→1,3,2
    //3,2,1→1,2,3
    //1,1,5→1,5,1
    public class Solution {
        public void nextPermutation(int[] num) {
            //解法:
            //  如果不存在更大的下一个排列的话,一定是当前的排列已经是最大,即降序排序即从后向前遍历应该是升序
            //下一个大,也就是说找到第一个不满足从后向前为升序的点,将其与后面的刚好比他大的数交换,然后这个点后面的点(不包含这个点)升序排序,因为升序排序表示字典序最小
            //如果要下一个大,也就是只能比当前大1,后面的只能升序,没有别的办法
            //  从后往前遍历,找到第一个不满足升序的点,将该点与后面所有点中大于这个数的第一个数与其交换
            //  然后将i+1位置与i+1后面的数升序排序(比当前大的字典序)
            if(num == null || num.length == 0) return;
            if (num.length == 1) return;

            int i = num.length - 2;
            //首先逆序检测是否升序
            while (i >= 0 && num[i] >= num[i + 1]) {
                i--;
            }
            //到头了
            //如果逆序到头都是升序,说明这个数组从正着来是降序,直接将数组变成升序排列然后输出
            if (i == -1) {
                Arrays.sort(num);
                return;
            }
            //没到头
            //如果上面的没走到,说明第i个位置小于第i+1个位置,将i位置与i位置后面的离他最近的数交换位置
            //然后将i+1位置与i+1后面的数升序排序
            for (int j = num.length - 1; j > i; j--) {
                if (num[i] < num[j]){
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                    Arrays.sort(num, i + 1, num.length);
                    break;
                }
            }
            return;
        }
    }
}
