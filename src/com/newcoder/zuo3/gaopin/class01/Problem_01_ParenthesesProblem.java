package com.newcoder.zuo3.gaopin.class01;

public class Problem_01_ParenthesesProblem {
    //套路:看见子数组子串:流程上考虑以每个位置开始的思路,结束看i+1位置能不能由i位置得到
    //1、已知一个字符串都是由左括号(和右括号)组成，判断该字符串是否是有效的括号组合。
    //
    //例子：
    //有效的括号组合:()(),(()),(()())
    //无效的括号组合:(,()),((),()(()

    //解法:找一个变量count,每次有(就++有)就--,如果中途发现count到0以下,则为false(如果中间出现除了()之外其他的字符,也直接返回false)
    public static boolean isValid(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            //如果出现了除了()之外的其他字符,直接false
            if (chars[i] != '(' && chars[i] != ')') return false;
            //如果遇到'(' ++
            if (chars[i] == '(') count++;
            //如果遇到')' --
            if (chars[i] == ')') count--;
            //如果中途count小于0,直接false
            if (count < 0) return false;
        }
        //返回count是否为0
        return count == 0;
    }

    //2、题目进阶：
    //已知一个字符串都是由左括号(和右括号)组成，返回最长有效括号子串的长度。
    //解法:dp[i]必须以该位置结尾的情况下最长有效子串长度是多少,dp[i-1]告诉dp[i]往前看多少
    public static int maxLength(String str) {
        if (str == null || "".equals(str)) return 0;

        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int pre = 0;    //表示上一个位置的坐标
        int result = 0; //表示结果
        for (int i = 1; i < chars.length; i++) {
            //是'('的位置填0,因为以'('结尾的为无效括号子串,因为初始化的时候都是0,所以不填了
            if (chars[i] == ')') {
                //距离最近的上一个有效子串的前一个位置,就是为了看这个位置上是不是(,能和刚来的)配上
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    //这个位置上是(,能和刚来的)配上,所以+2,后面的pre>0表示子串还有剩余,那就和pre上一个位置,
                    //也就是能保证连续的有效括号子串相加就是整体的长度
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    //for test
    //implement by zuo
    public static boolean isValid1(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        char[] chas = str.toCharArray();
        int status = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ')' && chas[i] != '(') {
                return false;
            }
            if (chas[i] == ')' && --status < 0) {
                return false;
            }
            if (chas[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public static int maxLength1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] dp = new int[chas.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chas[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(isValid1(str1));
        System.out.println(isValid(str1));
        System.out.println(maxLength1(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValid1(str2));
        System.out.println(isValid(str2));
        System.out.println(maxLength1(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValid1(str3));
        System.out.println(isValid(str3));
        System.out.println(maxLength1(str3));
        System.out.println(maxLength(str3));

    }

}
