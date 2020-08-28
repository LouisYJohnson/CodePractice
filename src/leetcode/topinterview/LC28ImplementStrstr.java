package leetcode.topinterview;

public class LC28ImplementStrstr {
    //实现 strStr() 函数。
    //给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
    //示例 1:
    //输入: haystack = "hello", needle = "ll"
    //输出: 2
    //示例 2:
    //输入: haystack = "aaaaa", needle = "bba"
    //输出: -1
    //说明:
    //当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    //对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/implement-strstr
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public int strStr(String haystack, String needle) {
            if (needle == null || haystack == null) {
                return -1;
            } else if (haystack.length() < needle.length()) {
                return -1;
            } else if (needle.length() == 0) {
                return 0;
            }
            char[] needle2Chars = needle.toCharArray();
            //kmp算法
            int[] next;
            if (needle.length() == 1) {
                next = new int[]{-1};   //如果needle长度为1,那么就返回一个只有-1的数组
            } else {
                next = new int[needle.length()];
                next[0] = -1;
                next[1] = 0;
                int pos = 2;
                int cn = 0;
                while (pos < needle2Chars.length) {
                    if (needle2Chars[pos - 1] == needle2Chars[cn]) {
                        next[pos++] = ++cn;
                    } else if (cn > 0) {
                        cn = next[cn];
                    } else {
                        next[pos++] = 0;
                    }
                }
            }

            char[] haystack2Chars = haystack.toCharArray();
            int si = 0;
            int mi = 0;
            while (si < haystack2Chars.length && mi < needle2Chars.length) {
                if (haystack2Chars[si] == needle2Chars[mi]) {
                    si++;
                    mi++;
                } else if (next[mi] == -1) {
                    si++;
                } else {
                    mi = next[mi];
                }
            }
            return mi == needle2Chars.length ? si - mi : -1;
        }
    }
}
