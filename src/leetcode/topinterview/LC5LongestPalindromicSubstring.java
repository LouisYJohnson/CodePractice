package leetcode.topinterview;

public class LC5LongestPalindromicSubstring {
    //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //示例 1：
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。
    //示例 2：
    //输入: "cbbd"
    //输出: "bb"
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/longest-palindromic-substring
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public String longestPalindrome(String s) {
            //Manacher算法
            //首先将其变为Manacher能够处理的字符数组
            char[] manacherChars = toManacherChars(s);
            int[] pArr = new int[manacherChars.length];
            int pR = -1;
            int center = -1;
            int maxLen = Integer.MIN_VALUE;
            int maxCenter = -1;

            for (int i = 0; i < manacherChars.length; i++) {
                //首先为pArr赋一个初始值(按照最小的那个扩)
                pArr[i] = pR > i ? Math.min(pR - i, pArr[2 * center - i]) : 1;
                //然后看能不能往两边扩
                while (i + pArr[i] < manacherChars.length && i - pArr[i] >= 0) {
                    if (manacherChars[i + pArr[i]] == manacherChars[i - pArr[i]]) {
                        pArr[i]++;
                    }else {
                        break;
                    }
                }
                //然后来更新pR与center,center与pR都是表示最右边界的,而不是最长回文串的
                if (i + pArr[i] > pR) {
                    pR = i + pArr[i];
                    center = i;
                }
                if (pArr[i] > maxLen) {
                    maxCenter = i;
                }
                maxLen = Math.max(maxLen, pArr[i]);
            }
            //得到了最长回文串在mamacherChars中的中心maxCenter和长度pArr[maxCenter],
            //注意,初始的Manacher算法算出来的center与pR是最右边界对应的中心和最右的下一个位置,而不是最长回文字符串
            //需要自己手动加入计算最长回文串的代码才行
            StringBuffer helpSB = new StringBuffer();
            int startIndex = 2 * maxCenter - (maxCenter + pArr[maxCenter] - 1);
            for (int i = startIndex; i <= maxCenter + pArr[maxCenter] - 1; i++) {
                if (manacherChars[i] != '#') {
                    helpSB.append(manacherChars[i]);
                }
            }
            return helpSB.toString();
        }

        public char[] toManacherChars(String s) {
            char[] res = new char[s.length() * 2 + 1];
            int helpIndex = 0;
            for (int i = 0; i < res.length; i++) {
                if ((i & 1) == 0) { //偶数放#
                    res[i] = '#';
                } else { //奇数,放原来的元素
                    res[i] = s.charAt(helpIndex++);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC5LongestPalindromicSubstring().new Solution();
        String test = "babadada";
        String res = solution.longestPalindrome(test);
        System.out.println(1);
    }
}
