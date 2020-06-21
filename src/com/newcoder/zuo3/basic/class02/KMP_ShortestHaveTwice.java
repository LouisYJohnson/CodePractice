package com.newcoder.zuo3.basic.class02;

public class KMP_ShortestHaveTwice {
    //给定一个字符串str1， 只能往str1的后面添加字符变成str2。
    //要求1： str2必须包含两个str1， 两个str1可以有重合， 但是不
    //能以同一个位置开头。
    //要求2： str2尽量短
    //最终返回str2
    //举例：
    //str1 = 123， str2 = 123123 时， 包含两个str1， 且不以相同
    //位置开头， 且str2最短。
    //str1 = 123123， str2 = 123123123 时， 包含两个str1， 且不
    //以相同位置开头， 且str2最短。
    //str1 = 111， str2 = 1111 时， 包含两个str1， 且不以相同位
    //置开头， 且str2最短。

    //其实就是求next数组,看字符串最后一个元素的后一个的信息值是多少,是几就在后面补上原字符串从几开始
    //因为next数组上的值和当前位置上的字符无关,所以next数组的大小可以比字符数组长度大1
    public static String answer(String str) {
        if (str == null || str.length() == 0) return "";
        if (str.length() == 1) {
            return str + str;
        }
        char[] chars = str.toCharArray();
//        if (str.length() == 2) {
//            if (chars[0] == chars[1]) return String.valueOf(chars[0]) + chars[0] + chars[0];
//            return String.valueOf(chars[0]) + chars[1] + chars[0] + chars[1];
//        }
        int lastNextLength = endNextLength(chars);
        String subString = str.substring(lastNextLength);
        return str + subString;
    }

    public static int endNextLength(char[] chas) {
        int[] next = new int[chas.length + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;

        while (pos < next.length) {
            if (chas[pos - 1] == chas[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next[chas.length];
    }

    public static void main(String[] args) {
        String test1 = "a";
        System.out.println(answer(test1));

        String test2 = "aa";
        System.out.println(answer(test2));

        String test3 = "ab";
        System.out.println(answer(test3));

        String test4 = "abcdabcd";
        System.out.println(answer(test4));

        String test5 = "abracadabra";
        System.out.println(answer(test5));
    }
}
