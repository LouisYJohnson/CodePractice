package leetcode.median;

public class LC92LengthOfLastWord {
    //题目描述
    //给出一个只包含大小写字母和空格的字符串s，请返回字符串中最后一个单词的长度
    //如果字符串中没有最后一个单词，则返回0
    //注意：单词的定义是仅由非空格字符组成的字符序列。
    //例如：
    //s ="Hello World",
    //返回5。
    public class Solution {
        public int lengthOfLastWord(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            String[] s2Words = s.split(" ");
            if (s2Words.length == 0) {
                return 0;
            }
            return s2Words[s2Words.length - 1].length();
        }
    }

    public static void main(String[] args) {
        String s = "    ";
        String[] test = s.split(" ");
        System.out.println(1);
    }
}
