package leetcode.top100interview;

public class LC415AddStrings {
    //给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
    //提示：
    //num1 和num2 的长度都小于 5100
    //num1 和num2 都只包含数字 0-9
    //num1 和num2 都不包含任何前导零
    //你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/add-strings
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public String addStrings(String num1, String num2) {
            if ((num1 == null && num2 == null) || (num1.length() == 0 && num2.length() == 0)) {
                return "0";
            } else if (num1 == null || num1.length() == 0) {
                return num2;
            } else if (num2 == null || num2.length() == 0) {
                return num1;
            }
            //转成char数组然后从右到左看进位即可
            char[] num1ToChars = num1.toCharArray();
            char[] num2ToChars = num2.toCharArray();
            //有可能有进位,所以要+1
            char[] res = new char[Math.max(num1ToChars.length, num2ToChars.length) + 1];
            res[0] = '0';
            //两个指针,分别指向这两个字符数组的最后一个位置,然后从最后开始加
            int helpIndexNum1 = num1ToChars.length - 1;
            int helpIndexNum2 = num2ToChars.length - 1;
            int flag = 0;   //进位值
            int resIndex;
            for (resIndex = res.length - 1; helpIndexNum1 >= 0 && helpIndexNum2 >= 0; ) {
                //上一位的进位信息和当前位的值进行相加
                //变整数的时候要-'0'变字符的时候要+'0'
                int temp = num1ToChars[helpIndexNum1--] - '0' + num2ToChars[helpIndexNum2--] - '0' + flag;
                res[resIndex--] = (char) (temp % 10 + '0');
                flag = temp / 10;
            }
            //到这里,会有的位没有走完,走完的变成-1,没走完的是第一个没走到的位置
            char[] longest = helpIndexNum1 >= 0 ? num1ToChars : num2ToChars;
            int longestIndex = helpIndexNum1 >= 0 ? helpIndexNum1 : helpIndexNum2;

            while (longestIndex >= 0) {
                int temp = longest[longestIndex--] - '0' + flag;
                res[resIndex--] = (char) (temp % 10 + '0');
                flag = temp / 10;
            }
            //到这里,都走完了,但是还有能有进位信息没有处理
            //因为这些数组都是前面没有0填充的,而且解数组也就是比最长数组大1(最多进一位),所以最终只看进位信息是多少
            //如果没有进位,肯定是在前面进位就用完了
            res[0] = (char) (flag + '0');

            if (res[0] == '0' && res.length == 1) {
                return "0";
            } else {
                return res[0] == '0' ? String.valueOf(res).substring(1) : String.valueOf(res);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC415AddStrings().new Solution();
        System.out.println(solution.addStrings("99", "9"));
        System.out.println(999+999);
    }
}
