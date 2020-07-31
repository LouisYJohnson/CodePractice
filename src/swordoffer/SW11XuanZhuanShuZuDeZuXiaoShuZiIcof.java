package swordoffer;

public class SW11XuanZhuanShuZuDeZuXiaoShuZiIcof {
    //把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    // 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
    // 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
    //示例 1：
    //输入：[3,4,5,1,2]
    //输出：1
    //示例 2：
    //输入：[2,2,2,0,1]
    //输出：0
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
    class Solution {
        public int minArray(int[] numbers) {
            //二分法
            //数组因为是递增排序(有可能有重复元素),而且被旋转,所以分为两部分
            //左半部分与右半部分,中间是那个最小的点
            //左半部分是一定大于等于右半部分的
            //所以用中间位置的元素和最右边的元素进行比较,并分情况更新左与右
            //为什么和最右边比而不和最左边比:如果中间元素大于最左边的话,是无法判断左右数组的
            //例如:当 i = 0, j = 4, m = 2i=0,j=4,m=2 时，有 numbers[m] > numbers[i] ，以下两示例得出不同结果。
            //numbers = [1, 2, 3, 4 ,5]numbers=[1,2,3,4,5] 旋转点 x = 0x=0 ： mm 在右排序数组（此示例只有右排序数组）；
            //numbers = [3, 4, 5, 1 ,2]numbers=[3,4,5,1,2] 旋转点 x = 3x=3 ： mm 在左排序数组。
            int left = 0;
            int right = numbers.length - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] > numbers[right]){ //mid在左数组
                    left = mid + 1; //+1是因为这里都大于right了,肯定不是最小值,去掉,少一次比较
                } else if (numbers[mid] < numbers[right]) { //mid在右数组
                    right = mid;    //不-1是因为,这里是小于right的,有可能是最小值,不能漏掉
                }else {
                    //由于重复元素的存在，我们并不能确定numbers[pivot]究竟在最小值的左侧还是右侧，
                    // 因此我们不能莽撞地忽略某一部分的元素。我们唯一可以知道的是，
                    // 由于它们的值相同，所以无论numbers[high]是不是最小值，都有一个它的「替代品」
                    // numbers[pivot]，因此我们可以忽略二分查找区间的右端点。
                    right--;
                }
            }
            return numbers[left];
        }
    }
}
