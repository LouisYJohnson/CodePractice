package com.newcoder.zuo3.advanced.class06;

public class unsolved_Code_05_PalindromeMinCut {
    //回文最少分割数
    //【题目】
    //给定一个字符串str， 返回把str全部切成回文子串的最小分割数。
    //【举例】
    //str="ABA"。
    //不需要切割， str本身就是回文串， 所以返回0。
    //str="ACDCDCDAD"。
    //最少需要切2次变成3个回文子串， 比如"A"、 "CDCDC"和"DAD"， 所以返回2。
    //递归:
    //递归函数功能:保证在当前位置的前面都是回文,返回后面字符串要切多少刀才能都变成回文子串的最少刀数
    //这第一刀只要是前面是回文串,那么这一刀切在哪里都可以,所以是要函数中遍历中的是递归,枚举所有可能性,在保证前面是回文串的前提下
    //在改动态规划的时候发现每个位置的值需要后面所有值,而且在得到值之前都需要进行是否是回文串的判断来判断是否要这个位置的值
    //那么问题就在于如何优化判断任意位置范围内的字符串是不是回文,因为检查[l,r]是不是回文很频繁,所以使用预处理数组来储存结果

    //判断是不是回文串
    public static boolean isP(char[] chars, int l, int r) {
        if (r - l == 0) {
            return true;
        }
        int mid = l + (r - l) / 2;

        for (int i = l; i <= mid; i++) {
            if (chars[i] != chars[r--]) return false;
        }
        return true;
    }

    public static int cut(char[] str, int i) {
        //base case
        if (i == str.length - 1) return 0;

        int min = Integer.MAX_VALUE;
        for (int j = i; j < str.length; j++) {
            if (isP(str, i, j)) {
                //因为递归的含义是从i位置(不包括i位置)之前的都是回文的前提下,所以这里确定了i到j是回文,下次就要从j+1开始了
                min = Math.min(min, cut(str, j + 1));
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min + 1;
//        return min;
    }

    // for test
    public static int minCut(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int len = chas.length;
        int[] dp = new int[len + 1];
        dp[len] = -1;
        boolean[][] p = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                if (chas[i] == chas[j] && (j - i < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }


    public static String getRandomStringOnlyAToD(int len) {
        int range = 'D' - 'A' + 1;
        char[] charArr = new char[(int) (Math.random() * (len + 1))];
        for (int i = 0; i != charArr.length; i++) {
            charArr[i] = (char) ((int) (Math.random() * range) + 'A');
        }
        return String.valueOf(charArr);
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int testTimes = 5;
        String str = null;
        for (int i = 0; i != testTimes; i++) {
            str = getRandomStringOnlyAToD(maxLen);
            char[] strChar = str.toCharArray();
            System.out.print("\"" + str + "\"" + " : ");
            System.out.println(minCut(str));
            System.out.print(" ||||" + cut(strChar,0));
            System.out.println();
        }
//        System.out.println(cut("BCC".toCharArray(), 0));

    }


}
