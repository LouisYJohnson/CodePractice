package leetcode.top100interview;

public class LC14LongestCommonPrefix {
    //编写一个函数来查找字符串数组中的最长公共前缀。
    //如果不存在公共前缀，返回空字符串 ""。
    //示例 1:
    //输入: ["flower","flow","flight"]
    //输出: "fl"
    //示例 2:
    //输入: ["dog","racecar","car"]
    //输出: ""
    //解释: 输入不存在公共前缀。
    //说明:
    //所有输入只包含小写字母 a-z 。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/longest-common-prefix
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            //依次遍历每一个字符串,更新最短的字符串前缀
            if (strs == null || strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++) {
                prefix = getLongestPrefix(prefix, strs[i]);
            }
            return prefix;
        }

        public String getLongestPrefix(String s1, String s2) {
            //找到s1与s2的最长前缀,并将s1转化为最长前缀
            int index = 0;
            while (index < s1.length() && index < s2.length()) {
                if (s1.charAt(index) == s2.charAt(index)) {
                    index++;
                }else {
                    break;
                }
            }
            return s1.substring(0, index);
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC14LongestCommonPrefix().new Solution();
        String[] test = new String[] {"aaa", "aa", "aaa"};
        solution.longestCommonPrefix(test);
    }
}
