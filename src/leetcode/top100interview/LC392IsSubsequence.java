package leetcode.top100interview;

public class LC392IsSubsequence {
    //给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    //你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
    //字符串的一个子序列是原始字符串删除一些（也可以不删除）
    // 字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    //示例 1:
    //s = "abc", t = "ahbgdc"
    //返回 true.
    //示例 2:
    //s = "axc", t = "ahbgdc"
    //返回 false.
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/is-subsequence
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public boolean isSubsequence(String s, String t) {
            //定义两个指针,分别指向两个字符串的第0个元素,如果元素相同都++,否则ti++
            //边界条件判断比较麻烦
            if (s == null && t == null) {
                return true;
            } else if (s == null || t == null) {
                return false;
            } else if (s.length() > t.length()) {
                return false;
            } else if (s.length() == 0) {
                return true;
            }

            int si = 0;
            int ti = 0;
            char[] s2Chars = s.toCharArray();
            char[] t2Chars = t.toCharArray();

            while (ti < t2Chars.length) {
                if (s2Chars[si] == t2Chars[ti]) {
                    si++;
                    ti++;
                    if (si == s2Chars.length) {
                        return true;
                    }
                } else {
                    ti++;
                }
            }
            return false;
        }
    }

}
