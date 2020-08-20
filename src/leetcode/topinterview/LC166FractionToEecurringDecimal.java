package leetcode.topinterview;


import java.util.HashMap;

public class LC166FractionToEecurringDecimal {
    //给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
    //如果小数部分为循环小数，则将循环的部分括在括号内。
    //示例 1:
    //输入: numerator = 1, denominator = 2
    //输出: "0.5"
    //示例 2:
    //输入: numerator = 2, denominator = 1
    //输出: "2"
    //示例 3:
    //输入: numerator = 2, denominator = 3
    //输出: "0.(6)"
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            //https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang/
            //为了判断是否出现循环小数，我们不应该判断是否出现了重复的商，
            // 而是应该判断是否出现了重复的被除数。
            //循环也很明显了。被除数除以除数，记录商。
            // 然后余数乘以 10 做为新的被除数继续除以除数。直到余数为 0 或者出现重复的被除数。
            //整数部分与小数部分分开存储

            //除法是分为整数部分和小数部分的,整数部分在第一次做除法的时候就一定可以被解决
            //首先确定符号
            String sign = "";
            if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
                sign = "-";
            }
            //转化为绝对值除法,但是这里要注意,如果是像这种写法的话,右边的int类型如果选负数最小值,再用abs就越界了
            //最后给long类型的就是负数了
            //所以应该先给long赋值,然后再abs
//            long num = Math.abs(numerator);
//            long den = Math.abs(denominator);
            long num = numerator;
            long den = denominator;
            num = Math.abs(num);
            den = Math.abs(den);

            //记录整数部分
            long integer = num / den;
            //计算余数
            num = num - integer * den;
            //申请一个map,来记录被除数以及被除数出现的位置
            HashMap<Long, Integer> helpMap = new HashMap<>();
            int index = 0;      //记录每一个被除数出现的位置
            int repeatIndex = -1;   //使用一个非法的下标来保存重复的位置
            StringBuilder helpSB = new StringBuilder();

            while (num != 0) {
                //余数乘以10作为新的被除数
                num *= 10;
                //如果被除数之前出现过,直接break
                if (helpMap.containsKey(num)) {
                    repeatIndex = helpMap.get(num);
                    break;
                }
                //否则,将helpMap中放入这个被除数以及被除数对应的下标
                //下标是从0开始的,表示从小数点后第一位开始向右边延申,如果重复出现,则重复的数为这个
                //被除数刚出现的位置,到第二次出现的位置的前一个位置
                helpMap.put(num, index++);  //每次index++,出现重复被除数的时候,这个位置指向的就是重复被除数出现的位置
                //将当前的商以及之前的商加在一起
                long decimalPlace = (num / den);
                helpSB.append(decimalPlace);
                //计算新的余数
                num = num - decimalPlace * den;
            }
            //是否存在小数
            if (helpMap.isEmpty()) {
                return sign + String.valueOf(integer);
            }

            //是否存在循环小数
            if (repeatIndex != -1) {
                String notRepeatString = helpSB.substring(0, repeatIndex);
                String repeatString = helpSB.subSequence(repeatIndex, index).toString();
                String res = String.valueOf(integer) + "." + notRepeatString + "(" + repeatString + ")";
                return sign + res;
            } else { //如果不存在循环小数
                String res = String.valueOf(integer) + "." + helpSB.toString();
                return sign + res;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC166FractionToEecurringDecimal().new Solution();
        solution.fractionToDecimal(-1, -2147483648);
    }
}
