package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;

public class LC131PalindromePartitioning {
    //给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
    //返回 s 所有可能的分割方案。
    //示例:
    //输入: "aab"
    //输出:
    //[
    //  ["aa","b"],
    //  ["a","a","b"]
    //]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/palindrome-partitioning
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
        public List<List<String>> partition(String s) {

            List<List<String>> res = new ArrayList<>();
            ArrayList<String> help = new ArrayList<>();
            process(s, 0, res, help);
            return res;
        }

        //回溯法
        //  不符合回文条件的,就不会进入下一层回溯
        //help用来记录每一个合法的树的路径
        public void process(String s, int start, List<List<String>> res, ArrayList<String> help) {
            //base case
            if (start == s.length()) {
                res.add(new ArrayList<>(help));
            }

            for (int i = start; i < s.length(); i++) {
                if (!isPalindrome(s.substring(start, i + 1))) {
                    continue;   //不是回文的切割,是不能进入下一层回溯的
                }

                help.add(s.substring(start, i + 1));
                process(s, i + 1, res, help);
                help.remove(help.size() - 1);
            }
        }

        //使用Manacher算法计算当前字符串是不是回文串(其实只要在pR第一次为最右侧的时候判断center是否为中心即可)
        public boolean isPalindrome(String s) {
            //首先构建manacher字符数组
            char[] s2Manacher = new char[s.length() * 2 + 1];
            int indexOfS = 0;
            for (int i = 0; i < s2Manacher.length; i++) {
                //偶数下标填'#',奇数下标填原始数组中的元素
                if ((i & 1) == 1) {
                    s2Manacher[i] = s.charAt(indexOfS++);
                }else {
                    s2Manacher[i] = '#';
                }
            }
            //装着每一个位置上的回文串长度
            int[] pRArr = new int[s2Manacher.length];
            //最长右边界以及最长右边界对应的中心位置
            int center = -1;
            int pR = -1;
            int maxLen = Integer.MIN_VALUE;
            for (int i = 0; i < pRArr.length; i++) {
                //首先为当前位置指定一个回文半径的初始值
                pRArr[i] = pR > i ? Math.min(pR - i, pRArr[2 * center - i]) : 1;
                //然后在初始值的基础上向两边扩(在未越界的前提下)
                while (i - pRArr[i] >= 0 && i + pRArr[i] < pRArr.length) {
                    if (s2Manacher[i - pRArr[i]] == s2Manacher[i + pRArr[i]]) {
                        pRArr[i]++;
                    }else {
                        break;
                    }
                }
                //然后来更新最右边界和最右边界(实际上是最右边界的下一个位置)对应的中心位置
                if (i + pRArr[i] > pR) {
                    pR = i + pRArr[i];
                    center = i;
                }
                maxLen = Math.max(maxLen, pRArr[i]);
            }
            //不能使用最右边界是否到达最右边来判断,因为最右边界是一定能到达字符串最后一个位置的下一个的(所有元素都遍历了)
            //最长的半径长度应该-1才是最长回文串对应的长度
            return maxLen - 1 == s.length();
        }
    }

    public static void main(String[] args) {
//        Solution solution = new LC131().new Solution();
//        solution.partition("aab");
        Solution solution = new LC131PalindromePartitioning().new Solution();
        System.out.println(solution.isPalindrome("aaa"));
    }


}
