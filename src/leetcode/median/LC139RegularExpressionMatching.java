package leetcode.median;

public class LC139RegularExpressionMatching {
    //题目描述
    //请实现支持'.'and'*'.的通配符模式匹配
    //'.' 可以匹配任何单个字符。↵'*' 可以匹配任何字符序列（包括空序列）。
    // ↵↵匹配应该覆盖整个输入字符串（而不是部分）。
    // ↵函数声明为：↵bool isMatch(const char *s, const char *p)↵↵
    // 下面给出一些样例：↵isMatch("aa","a") → false↵
    // isMatch("aa","aa") → true↵
    // isMatch("aaa","aa") → false↵
    // isMatch("aa", "a*") → true↵
    // isMatch("aa", ".*") → true↵
    // isMatch("ab", ".*") → true↵
    // isMatch("aab", "c*a*b") → true

    public static boolean isMatch(String s, String p) {
        //使用递归函数解决
        //递归函数功能:
        //  传入两个字符数组与二者开始的下标返回以二者下标开始二者是否匹配
        if (s == null || p == null) {
            return false;
        }

        char[] s2Chars = s.toCharArray();
        char[] p2Chars = p.toCharArray();
        return process(s2Chars, p2Chars, 0, 0);
    }

    //递归函数功能:
    //传入两个字符数组与二者开始的下标返回以二者下标开始二者是否匹配
    //函数中分为pChars下一个下标上的元素是"*"与不是"*"的情况
    public static boolean process(char[] sChars, char[] pChars, int si, int pi) {
        //base case
        if (pi == pChars.length) return si == sChars.length;

        //如果下一个位置上不是*
        //要么已经到头了,要么下一个位置是其他字符
        if (pi + 1 == pChars.length || pChars[pi + 1] != '*') {
            //首先str不能到尽头,还要有字符能和当前的exp来比
            //当前位置的exp和str要相同才能返回true
            //当前位置进入子进程也要返回true
            //三者结合都是true才行
            return si != sChars.length
                    &&
                    (sChars[si] == pChars[pi] || pChars[pi] == '.')
                    &&
                    process(sChars, pChars, si + 1, pi + 1);
        }
        //如果下一个位置上是*
        //能走到这里说明不符合上面的if条件,表明exp没有到最后一个并且下一个位置是'*'
        //要看这一个'*'能在原字符串str中对应多少个相同的字符(0~n个,由原字符串决定)
        //首先要看'*'前面的字符(也就是当前位置)是什么
        while (si != sChars.length && (sChars[si] == pChars[pi] || pChars[pi] == '.')) {
            if (process(sChars, pChars, si, pi + 2)) {
                return true;
            }
            si++;
        }
        return process(sChars, pChars, si, pi + 2);
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }
}
