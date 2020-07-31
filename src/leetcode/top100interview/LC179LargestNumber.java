package leetcode.top100interview;

import java.util.Arrays;
import java.util.Comparator;

public class LC179LargestNumber {
    //给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
    //示例 1:
    //输入: [10,2]
    //输出: 210
    //示例 2:
    //输入: [3,30,34,5,9]
    //输出: 9534330
    //说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/largest-number
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public String largestNumber(int[] nums) {
            //这题就是排列字符串获得最大字典序的翻版
            if (nums == null || nums.length == 0) {
                return "";
            }
            Integer[] helpNums = new Integer[nums.length];
            for (int i = 0; i < helpNums.length; i++) {
                helpNums[i] = nums[i];
            }
            //比较器对组合后的字符串降序排序
            Comparator<Integer> myComparator = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    String temp1 = o1 + "" + o2;
                    String temp2 = o2 + "" + o1;
                    return temp2.compareTo(temp1);
                }
            };
            Arrays.sort(helpNums, myComparator);
            StringBuffer helpSB = new StringBuffer();
            for (int i = 0; i < helpNums.length; i++) {
                helpSB.append(helpNums[i]);
            }
            String res = helpSB.toString();
            //特殊case,如果全是0,就返回1个0
            if ('0' == res.charAt(0)) {
                return "0";
            }
            return helpSB.toString();
        }
    }
}
