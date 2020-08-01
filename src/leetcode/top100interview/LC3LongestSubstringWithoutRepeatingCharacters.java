package leetcode.top100interview;

import java.util.LinkedList;

public class LC3LongestSubstringWithoutRepeatingCharacters {
    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    //示例 1:
    //输入: "abcabcbb"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    //示例 2:
    //输入: "bbbbb"
    //输出: 1
    //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    //示例 3:
    //输入: "pwwkew"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            //滑动窗口+linkedList
            LinkedList<Character> helpList = new LinkedList<>();
            int right = 0;
            int res = 0;
            char[] s2Chars = s.toCharArray();

            while (right < s2Chars.length) {
                if (!helpList.contains(s2Chars[right])) {   //如果不包含右边界上的元素,那就放到链表中
                    helpList.addLast(s2Chars[right]);
                    res = Math.max(res, helpList.size());
                    right++;
                } else {    //否则左边开始动直到清除的元素为当前右边界上的元素位置
                    while (!helpList.isEmpty()) {
                        if (helpList.peekFirst() == s2Chars[right]) {
                            helpList.removeFirst();
                            break;
                        }else {
                            helpList.removeFirst();
                        }
                    }
                    //删完了不合格的,当前位置的别忘了加进去
                    helpList.addLast(s2Chars[right]);
                    right++;
                }
            }
            return res;
        }
    }
}
