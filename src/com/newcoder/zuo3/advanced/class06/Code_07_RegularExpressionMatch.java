package com.newcoder.zuo3.advanced.class06;

public class Code_07_RegularExpressionMatch {
    //字符串匹配问题
    //【题目】
    //给定字符串str， 其中绝对不含有字符'.'和'*'。 \
    //再给定字符串exp， 其中可以含有'.'或'*'， '*'字符不能是exp的首字符， 并且任意两个'*'字符不相邻。
    // exp中的'.'代表任何一个字符，exp中的'*'表示'*'的前一个字符可以有0个或者多个。
    // 请写一个函数， 判断str是否能被exp匹配。
    //【举例】
    //str="abc"， exp="abc"， 返回true。
    //str="abc"， exp="a.c"， exp中单个'.'可以代表任意字符， 所以返回true。
    //str="abcd"， exp=".*"。 exp中'*'的前一个字符是'.'， 所以可表示任意数量的'.'字符， 当
    //exp是"...."时与"abcd"匹配， 返回true。
    //str=""， exp="..*"。 exp中'*'的前一个字符是'.'， 可表示任意数量的'.'字符， 但是".*"之前还
    //有一个'.'字符， 该字符不受'*'的影响， 所以str起码有一个字符才能被exp匹配。 所以返回
    //false。
    //递归:str从i开始到最后能否被exp从j开始到最后匹配出来,P(i,j)就是这个意思.
    //初始过程调P(0,0)
    //递归中的情况分为:
    //exp下一个位置是*与下一个位置不是*分别该怎么办

    //首先判断str与exp是否有效
    //str:绝对不含有.和*
    //exp:可以含有'.'或'*'， '*'字符不能是exp的首字符， 并且任意两个'*'字符不相邻。
    public static boolean isValid(char[] str, char[] exp) {
        //str:
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.' || str[i] == '*') {
                return false;
            }
        }
        //exp:
        if (exp[0] == '*') return false;
        for (int i = 1; i < exp.length; i++) {
            if (exp[i] == '*' && exp[i - 1] == '*') {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) return false;
        char[] strChars = str.toCharArray();
        char[] expChars = exp.toCharArray();
        if (!isValid(strChars, expChars)) return false;
        return process(strChars, expChars, 0, 0);
    }

    //递归:str从i开始到最后能否被exp从j开始到最后匹配出来,P(i,j)就是这个意思.
    //初始过程调P(0,0)
    //递归中的情况分为:
    //exp下一个位置是*与下一个位置不是*分别该怎么办
    //不是*,判断当前位置是否匹配然后进入子进程
    //是*,看*能够对应0~n个相同字符,然后进入子进程
    public static boolean process(char[] str, char[] exp, int si, int ei) {
        //base case:
        //因为每次的子过程都会向前进1,而在==length-1的时候,实际上是还是需要判断的,所以base case来到了length的位置才算做走完
        if (ei == exp.length) {
            return si == str.length;
        }
        //exp下一个位置不是*的时候(要么exp已经到了最后一个,要不就是没到尽头但是下一个位置不是*)
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {  //exp不能到尽头且下一个位置不是*
            //str不能到尽头,且str的字符与exp的当前位置字符相同或者exp当前位置字符是'.'
            return si != str.length && (str[si] == exp[ei] || exp[ei] == '.')
                    && process(str, exp, si + 1, ei + 1);
        }
        //exp下一个位置是*的时候,如果能走到这里证明上面的if条件都不成立
        //也就是说exp没到最后一个,并且下一个位置是'*',而且exp当前位置的字符还能和exp中*前面的字符对应上
        //那就要看exp中*前面的字符能拉伸成1到多少个能对应上str中的子串,比如aaaab,对应*ab的话,明显*a要拉伸成4个a才行
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }
        //如果是*还对应不上,*直接当作0个字符处理
        return process(str, exp, si, ei + 2);
    }

    //for test
    //动态规划,巨难无比...
    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));
    }
}
