package com.newcoder.zuo3.advanced.class07;

public class Code_01_Scramble_String {
    //给定一个长度大于1的字符串， 我们可以把这个字符串分成两个非空的部分，
    //并且每个部分还能细分下去，
    //并且可以用二叉树的形式来表达， 比如
    //字符串 s1 = "great":
    //可以分解成这么一个样子（这只是其中一种分解结构）
    //great
    /// \
    //gr eat
    /// \ / \
    //g r e at
    /// \
    //a t
    //我们说s1的搅乱串， 指的是在任意一种分解结构中， 随意交换某个节点的左
    //右两个孩子所形成的字符串。
    //比如我们可以选择在上面的分解结构中， 交换“gr”这个节点的孩子节点， 形
    //成的树为：
    //rgeat
    /// \
    //rg eat
    /// \ / \
    //r g e at
    /// \
    //a t
    //那么“rgeat”， 是“great”的搅乱串。
    //同样， 我们可以继续交换“eat”节点的左右孩子， 形成：
    //rgtae
    /// \
    //rg tae
    /// \ / \
    //r g ta e
    /// \
    //t a
    //那么“rgtae”， 是“great”的搅乱串。
    //所以一个字符串的搅乱串是非常之多的， 分解结构本身就有很多种，
    //而且每一种分解结构都可以随意交换任意一个节点的左右孩子。
    //给定两个字符串s1和s2， 判断s2是不是s1的搅乱串。

    //字符种类一样数量一样不一定是搅乱串
    //比如1234不能变成2413
    //其实关键在于第一刀切在哪里,第一刀切下去,这刀两边的元素就可以随便动了.因为分解结构是固定的,不固定的只是其中固定元素的交换
    //所以标准就是:例:如果两个字符串,都分成7|5,并且左右两部分分别是搅乱串,那这两个字符串就是搅乱串,
    // 如果一个分成7|5一个分成5|7,并且5与5是搅乱串,7与7是搅乱串,则这两个字符串是搅乱串

    //递归为:递归为:在两个字符串上分别从L1,L2位置上开始,向右取size部分,判断这两个部分是否是搅乱串
    public static boolean isScramble1(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int size = s1.length();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        return process(chars1, chars2, 0, 0, size);
    }

    //size - part就是右边剩的部分
    public static boolean process(char[] chars1, char[] chars2, int l1, int l2, int size) {
        //base case
        if (size == 1) return chars1[l1] == chars2[l2];

        for (int part = 1; part < size; part++) {
            if (process(chars1, chars2, l1, l2, part) && process(chars1, chars2, l1 + part, l2 + part, size - part)
            ||
                process(chars1, chars2, l1, l2 + size - part, part) && process(chars1, chars2, l1 + part, l2, size - part)) {
                return true;
            }
        }
        return false;
    }

    //for test
    public static boolean isScramble2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int N = s1.length();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        boolean[][][] dp = new boolean[N][N][N + 1];
        for (int L1 = 0; L1 < N; L1++) {
            for (int L2 = 0; L2 < N; L2++) {
                dp[L1][L2][1] = chs1[L1] == chs2[L2];
            }
        }
        for (int size = 2; size <= N; size++) {
            for (int L1 = 0; L1 <= N - size; L1++) {
                for (int L2 = 0; L2 <= N - size; L2++) {
                    for (int p = 1; p < size; p++) {
                        if ((dp[L1][L2][p] && dp[L1 + p][L2 + p][size - p])
                                || (dp[L1][L2 + size - p][p] && dp[L1 + p][L2][size
                                - p])) {
                            dp[L1][L2][size] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][N];
    }

    public static void main(String[] args) {
        String test1 = "bcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcdebcde";
        String test2 = "cebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebdcebd";
        // System.out.println(isScramble1(test1, test2));
        System.out.println(isScramble2(test1, test2));
    }
}
