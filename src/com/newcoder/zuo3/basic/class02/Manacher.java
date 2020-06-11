package com.newcoder.zuo3.basic.class02;

public class Manacher {
    //为解决奇偶回文问题,在字符串的每一个字符的左右分别添加符号(符号是随意的,不影响结果)
    public static char[] manacherString(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            //一个元素的左右加上#
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str.length() == 0 || str == null) {
            return 0;
        }
        //先处理字符串
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];//每个位置上的回文半径,包括自身
        int index = -1;//最大回文半径对应的回文中心
        int pR = -1;//最大回文的右边界
        int max = Integer.MIN_VALUE;
        //从头遍历处理后的字符串数组,查找最大回文半径并更新
        for (int i = 0; i < charArr.length; i++) {
            //选择pR-i与pArr[index*2-i](即对称位置)中更小的一个作为i的初始半径(如果当前位置i在pR的内侧)
            //如果在外侧或者正好在边界,就以1为初始半径
            pArr[i] = pR > i ? Math.min(pR - i, pArr[index * 2 - i]) : 1;
            //以刚才定好的初始半径向外扩
            //i-pArr[i]是比i点对应的半径(包括i点自身)再往前1个单位的位置
            //i+pArr[i]是比i点对应的半径(包括i点自身)再往后1个单位的位置
            //先看这个位置是否越界(超过数组自身),然后再判断这个位置是不是回文
            while (i - pArr[i] > -1 && pArr[i] + i < charArr.length) {
                if (charArr[i - pArr[i]] == charArr[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            //更新最外边界,与最长回文子串的长度(因为处理的串是经过加符号的串,所以最终的长度就是加符号串的最长回文半径-1)
            //先更新最外边界,与最外边界对应的中心
            //不太明白这里为什么是i+pArr[i]>pR
            //pR表示的是最大回文右边界,因为i是从0开始的,所以要加上这个包括自身的回文半径长度pArr[i]才是pR
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            //更新最长回文子串的长度
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321cba";
        System.out.println(maxLcpsLength(str1));
    }

//    //为解决奇偶回文问题,在字符串的每一个字符的左右分别添加符号(符号是随意的,不影响结果)
//    public static char[] manacherString(String str) {
//        char[] charArr = str.toCharArray();
//        char[] res = new char[str.length() * 2 + 1];
//        int index = 0;
//        for (int i = 0; i != res.length; i++) {
//            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
//        }
//        return res;
//    }
//
//    public static int maxLcpsLength(String str) {
//        if (str == null || str.length() == 0) {
//            return 0;
//        }
//        char[] charArr = manacherString(str);
//        int[] pArr = new int[charArr.length];
//        int index = -1;
//        int pR = -1;
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i != charArr.length; i++) {
//            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
//            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
//                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
//                    pArr[i]++;
//                else {
//                    break;
//                }
//            }
//            if (i + pArr[i] > pR) {
//                pR = i + pArr[i];
//                index = i;
//            }
//            max = Math.max(max, pArr[i]);
//        }
//        return max - 1;
//    }
//
//    public static void main(String[] args) {
//        String str1 = "abc1234321ab";
//        System.out.println(maxLcpsLength(str1));
//    }
}
