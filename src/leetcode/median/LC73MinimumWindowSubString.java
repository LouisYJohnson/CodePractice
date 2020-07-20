package leetcode.median;

public class LC73MinimumWindowSubString {
    //题目描述
    //给出两个字符串S和T，要求在O（n）的时间复杂度内在S中找出最短的包含T中所有字符的子串。
    //例如：
    //S ="ADOBECODEBANC"
    //T ="ABC"
    //找出的最短子串为"BANC".
    //注意：
    //如果S中没有包含T中所有字符的子串，返回空字符串 “”；
    //满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
    public class Solution {
        /**
         *
         * @param S string字符串
         * @param T string字符串
         * @return string字符串
         */
        public String minWindow (String S, String T) {
            // write code here
            //解法:滑动窗口,以每一个位置开始得到的最小串
            int strLen = 0;
            int left = 0;
            int right = 0;
            while (right < S.length()) {
            }
            return null;
        }
    }
}
