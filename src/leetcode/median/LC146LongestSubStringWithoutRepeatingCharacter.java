package leetcode.median;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LC146LongestSubStringWithoutRepeatingCharacter {
    //题目描述
    //给定一个字符串，找出最长的不具有重复字符的子串的长度。
    // 例如，“abcabcbb”不具有重复字符的最长子串是“abc”，长度为3。
    // 对于“bbbbb”，最长的不具有重复字符的子串是“b”，长度为1。
    public class Solution {
        /**
         * @param s string字符串
         * @return int整型
         */
        public int lengthOfLongestSubstring(String s) {
            // write code here
            //滑动窗口,窗口右边新进来值如果在当前窗口中没有,就一直往右边扩,
            //  如果右边进来的值当前窗口有了,就往右缩,缩到没有为止
            //每次都要更新窗口长度
            HashMap<Character, Integer> hashMap = new HashMap<>();
            int leftBound = 0;
            int maxLen = 0;
            char[] chars = s.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                if (!hashMap.containsKey(chars[i])) {
                    hashMap.put(chars[i], i);
                    maxLen = Math.max(maxLen, i - leftBound + 1);
                } else {
                    leftBound = hashMap.get(chars[i]) + 1;
                    //将leftBound之前的所有字符从HashMap中删去,否则会误识别
                    Iterator<Map.Entry<Character, Integer>> iterator = hashMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Character, Integer> entry = iterator.next();
                        Character key = entry.getKey();
                        Integer value = entry.getValue();
                        if (value < leftBound) {
                            iterator.remove();
                        }
                    }
                    hashMap.put(chars[i], i);
                }
            }
            return maxLen;
        }
    }
}
