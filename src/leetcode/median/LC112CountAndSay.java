package leetcode.median;

public class LC112CountAndSay {
    //题目描述
    //count-and-say数列的前几项如下：
    //
    //1, 11, 21, 1211, 111221, ...
    //1读作“1个1”或11
    //11读作“2个1“或者21
    //21读作”1个2，1个1“或者1211
    //给出一个整数n，请给出序列的第n项
    //注意：序列中的数字用字符串表示

    public class Solution {
        /**
         * @param n int整型
         * @return string字符串
         */
        public String countAndSay(int n) {
            // write code here
            if (n < 1) return "";
            int i = 1;
            String res = "1";

            //每次都把上一次的结果拿进来作为本次的输入
            while (i < n) {
                res = countN(res);
                i++;
            }
            return res;
        }

        public String countN(String s) {
            char[] s2Chars = s.toCharArray();
            StringBuffer helpSB = new StringBuffer();
            //两个指针,用来数重复的字符
            int pre = 0;
            int next = 0;

            while (pre < s2Chars.length) {
                while (next < s2Chars.length && s2Chars[pre] == s2Chars[next]) {
                    next++;
                }
                //上面的while是如果相等再++,也就是说,得到的next要么就是s2Chars的长度,要么就是第一个不重复字符的下标

                //next-pre为有几个重复的数,那个重复的数就在pre的位置上
                helpSB.append(next - pre);
                helpSB.append(s2Chars[pre]);
                //加完结果将下一个位置的数更替为next位置
                pre = next;
            }
            return helpSB.toString();
        }

    }
}
