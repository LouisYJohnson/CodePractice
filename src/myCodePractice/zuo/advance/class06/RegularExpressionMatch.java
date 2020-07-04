package myCodePractice.zuo.advance.class06;

public class RegularExpressionMatch {
    //字符串匹配问题
    //【题目】
    //给定字符串str， 其中绝对不含有字符'.'和'*'。 再给定字符串exp， 其中可以含有'.'或'*'， '*'
    //字符不能是exp的首字符， 并且任意两个'*'字符不相邻。 exp中的'.'代表任何一个字符，
    //exp中的'*'表示'*'的前一个字符可以有0个或者多个。 请写一个函数， 判断str是否能被exp
    //匹配。
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

    //首先检查str和exp是否合法
    //给定字符串str， 其中绝对不含有字符'.'和'*'。 再给定字符串exp， 其中可以含有'.'或'*'， '*'
    //字符不能是exp的首字符， 并且任意两个'*'字符不相邻。
    public static boolean isValid(String str, String exp) {
        if (str == null || str.length() == 0 || exp == null || exp.length() == 0) return false;

        //判断str
        char[] str2Chars = str.toCharArray();
        for (int indexStr = 0; indexStr < str.length(); indexStr++) {
            if (str2Chars[indexStr] == '.' || str2Chars[indexStr] == '*') {
                return false;
            }
        }
        //判断exp
        char[] exp2Chars = exp.toCharArray();
        if (exp2Chars[0] == '*') return false;
        for (int indexExp = 1; indexExp < exp.length(); indexExp++) {
            if (exp2Chars[indexExp] == '*' && exp2Chars[indexExp - 1] == '*') {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (!isValid(str, exp)) return false;
        char[] str2Chars = str.toCharArray();
        char[] exp2Chars = exp.toCharArray();

        return process(str2Chars, exp2Chars, 0, 0);
    }

    //递归函数功能:
    //  给定str与exp的字符数组,和各自的起始坐标,返回能否由这两个起始坐标开始让str与exp匹配
    public static boolean process(char[] str2Chars, char[] exp2Chars, int si, int ei) {
        //base case
        if (ei == exp2Chars.length) return si == str2Chars.length;
        //情况分为exp下一个位置是'*'和下一个位置不是'*'的时候

        //首先讨论exp下一个位置不是'*'
        //  要么exp已经到头了,要么下一个位置是'.'或者其他字符
        if (ei + 1 == exp2Chars.length || exp2Chars[ei + 1] != '*') {
            //首先str不能到尽头,还要有字符能和当前的exp来比
            //当前位置的exp和str要相同才能返回true
            //当前位置进入子进程也要返回true
            //三者结合都是true才行
            return si != str2Chars.length
                    && (str2Chars[si] == exp2Chars[ei] || exp2Chars[ei] == '.')
                    && process(str2Chars, exp2Chars, si + 1, ei + 1);
        }
        //然后讨论exp下一个位置是'*'
        //能走到这里说明不符合上面的if条件,表明exp没有到最后一个并且下一个位置是'*'
        //要看这一个'*'能在原字符串str中对应多少个相同的字符(0~n个,由原字符串决定)
        //首先要看'*'前面的字符(也就是当前位置)是什么
        while (si != str2Chars.length && (str2Chars[si] == exp2Chars[ei] || exp2Chars[ei] == '.')) {
            //如果一个字符也不对应,直接进入忽略exp中"?*"这两个字符(?代表任意字符)的子过程
            if (process(str2Chars, exp2Chars, si, ei + 2)) {
                return true;
            }
            //否则,将si中满足条件的字符全都找出来,直到找不出来(到末尾或不相等)为止
            si++;
        }
        return process(str2Chars, exp2Chars, si, ei + 2);
    }
}
