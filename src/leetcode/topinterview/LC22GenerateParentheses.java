package leetcode.topinterview;

import java.util.ArrayList;
import java.util.List;

public class LC22GenerateParentheses {
    //数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    //示例：
    //输入：n = 3
    //输出：[
    //       "((()))",
    //       "(()())",
    //       "(())()",
    //       "()(())",
    //       "()()()"
    //     ]
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/generate-parentheses
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public List<String> generateParenthesis(int n) {
            //回溯法
            //定义两个变量,左边的括号数与右边的括号数
            //左边的括号数必须大于右边的括号数,在我的解法中,就是左边括号剩余的个数必须小于右边括号剩余的个数
            int left = n;
            int right = n;
            List<String> res = new ArrayList<>();
            StringBuffer helpString = new StringBuffer();
            process(n, left, right, helpString, res);
            return res;
        }

        public void process(int n, int left, int right, StringBuffer helpString, List<String> res) {
            //base case
            if (right == 0 && left == right) {  //只有右边用完了才是真的两个都用完了,因为优先(加
                String temp = helpString.toString();
                res.add(temp);
                return;
            }

            //如果左边剩的少于右边,才加右边的括号
            //加完了要删除这个加入的括号,才能进入本层的其他判断,因为在这一层,left和right都是不变的
            if (left < right) {
                helpString.append(")");
                process(n, left, right - 1, helpString, res);
                helpString.deleteCharAt(helpString.length() - 1);
            }
            //优先左边括号加
            if (left > 0) {
                helpString.append("(");
                process(n, left - 1, right, helpString, res);
                helpString.deleteCharAt(helpString.length() - 1);
            }
        }
    }
}
