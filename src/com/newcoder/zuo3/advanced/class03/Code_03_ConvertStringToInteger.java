package com.newcoder.zuo3.advanced.class03;

public class Code_03_ConvertStringToInteger {
    //将整数字符串转成整数值
    //【题目】
    //给定一个字符串str， 如果str符合日常书写的整数形式， 并且属于32
    //位整数的范围， 返回str所代表的整数值， 否则返回0。
    //【举例】
    //str="123"， 返回123。
    //str="023"， 因为"023"不符合日常的书写习惯， 所以返回0。
    //str="A13"， 返回0。
    //str="0"， 返回0。
    //str="2147483647"， 返回2147483647。
    //str="2147483648"， 因为溢出了， 所以返回0。
    //str="-123"， 返回-123。
    //先判断是否是有效的数,然后再转成整数
    //在任何语言中,负数的最小值的绝对值总是比整数的最大值大1,
    // 所以这种转换的题,都是按照负数转换,然后根据需要转成正数或者负数,如果说等于负数的最小还要正数,
    // 那就溢出了,如果按照正数转的话,负数的最小是转不过来的,
    // 而且正数的最大如果是负数最小的绝对值,也不知道溢出了
    public static int convert(String str) {
        if (str == null || "".equals(str)) return 0;
        char[] chars = str.toCharArray();
        if (!isValid(chars)) return 0;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        boolean postiveFlag = chars[0] == '-' ? true : false;
        for (int i = postiveFlag ? 0 : 1; i < chars.length; i++) {
            cur = '0' - chars[i];
            if ((res < minq) || (res == minq && cur < minr)) return 0;
            //确定不会越界,才变
            res = res * 10 + cur;
        }
        //如果说转后的数是负数最小数但是还要成为正数,是不可能的,因为最大正数的绝对值是比最小负数的绝对值还要小1的
        if (postiveFlag && res == Integer.MIN_VALUE) return 0;
        return postiveFlag ? -res : res;

    }

    //此函数用于检测字符数组对应数字是否符合标准
    public static boolean isValid(char[] chars) {
        //针对第一位的情况
        if (chars[0] == '0' && chars.length > 1) return false;
        if (chars[0] == '-' && chars[1] == '0') return false;
        if (chars.length == 1 && chars[0] == '-') return false;
        if (chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')) return false;
        //针对其余位置上的元素的情况
        for (int index = 1; index < chars.length; index++) {
            if (chars[index] < '0' || chars[index] > '9') return false;
        }
        return true;
    }
}
