package leetcode.median;

import java.util.ArrayList;

public class LC138GenerateParentheses {
    //题目描述
    //给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
    //例如，给出n=3，解集为：
    //"((()))", "(()())", "(())()", "()(())", "()()()"
    //
    //Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
    //
    //For example, given n = 3, a solution set is:
    //
    //"((()))", "(()())", "(())()", "()(())", "()()()"
    public ArrayList<String> generateParenthesis (int n) {
        // write code here
        //使用递归做
        ArrayList<String> res = new ArrayList<>();
        process("", n, 0, 0, res);
        return res;
    }

    //递归函数功能
    //  给定左边括号个数与右边括号个数与当前对应括号剩余个数已经排好的字符串String,与左右括号的总个数n
    //  将所有可能的排列都放入传入的res中
    //(的个数始终大于等于)的个数,base case为右边的)用完了就将当前排好的字符串放入res中
    public void process(String s, int n, int leftNum, int rightNum, ArrayList<String> res) {
        //base case
        if (rightNum == n) {
            res.add(s);
            return;
        }

        //因为左边括号个数必须始终大于等于右边,所以判断条件应该只有左边大于右边的时候,右边才考虑加入字符串
        if (leftNum < n) {
            process(s + "(", n, leftNum + 1, rightNum, res);
        }

        if (leftNum > rightNum) {
            process(s + ")", n, leftNum, rightNum + 1, res);
        }

    }
}
