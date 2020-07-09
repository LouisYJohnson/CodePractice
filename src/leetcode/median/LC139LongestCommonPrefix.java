package leetcode.median;

import java.util.HashSet;

public class LC139LongestCommonPrefix {
    //题目描述
    //编写一个函数来查找字符串数组中的最长公共前缀。
    //Write a function to find the longest common prefix string amongst an array of strings.

    public class Solution {
        /**
         * @param strs string字符串一维数组
         * @return string字符串
         */
        public String longestCommonPrefix(String[] strs) {
            // write code here
            if (strs == null || strs.length == 0) return "";
            int strMaxLen = 0;
            for (int i = 0; i < strs.length; i++) {
                strMaxLen = Math.max(strMaxLen, strs[i].length());
            }

            int tempIndex = 0;
            //res保存了每次找子串的前一次的结果,一旦遇到不满足的就将代表上一次结果的res,return
            //如果长度到i-1的所有字符串数组中的子串都满足,才更新res
            String res = "";
            HashSet<String> helpHashSet = new HashSet<>();
            for (int i = 1; i <= strMaxLen; i++) {
                //i表示第i-1位为结尾元素(subString取的是左闭右开区间)
                //每个字符串从0到i-1组成的子字符串都放到HashSet中,如果有不存在的,就返回
                //先将第一个字符串的子串放进去(必须满足不越界才能放,如果这个越界了后面的也不用看了,肯定不存在
                // 比第一个元素还长的前缀)
                if (i <= strs[0].length()) {
                    helpHashSet.add(strs[0].substring(0, i));
                } else {
                    return res;
                }
                //第一个字符串的子串放到HashSet中
                //其余的字符串在不越界的前提下将同样长度的子串放到set中,如果有相同的add方法返回true,此时返回res
                for (int j = 1; j < strs.length; j++) {
                    if (i > strs[j].length() || helpHashSet.add(strs[j].substring(0, i))) {
                        return res;
                    }
                }
                res = strs[0].substring(0, i);
            }
            return res;
        }
    }
}
