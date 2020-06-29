package com.newcoder.zuo3.advanced.class04;

public class Code_01_AddMinusMultiDivideByBit {
    //题目一
    //只用位运算不用算术运算实现整数的加减乘除运算
    //【题目】
    //给定两个32位整数a和b， 可正、 可负、 可0。 不能使用算术运算
    //符， 分别实现a和b的加减乘除运算。
    //【要求】
    //如果给定的a和b执行加减乘除的某些结果本来就会导致数据的
    //溢出， 那么你实现的函数不必对那些结果负责
    public static int add(int a, int b) {
        int forwardInfo = a & b;
        int noForwardInfo = a ^ b;
        while (forwardInfo != 0) {
            forwardInfo = forwardInfo << 1;
            int help = forwardInfo;
            forwardInfo = forwardInfo & noForwardInfo;
            noForwardInfo = help ^ noForwardInfo;
        }
        return noForwardInfo;
    }

    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int negNum(int num) {
        return add(~num, 1);
    }

    public static int multi(int a, int b) {
        int res = 0;
        int count = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = add(res, a << count);
            }
            b = b >>> 1;
            count++;
        }
        return res;
    }


    public static int divide(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("divisor is 0");
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            int res = div(add(a, 1), b);
            return add(res, div(minus(a, multi(res, b)), b));
        } else {
            return div(a, b);
        }
    }

    public static int div(int a, int b) {
        int helpA = a < 0 ? negNum(a) : a;
        int helpB = b < 0 ? negNum(b) : b;

        int help = 1 << 30;
        int res = 0;
        while (minus(helpA, multi(helpB, help)) != 0) {
            if (minus(helpA, multi(helpB, help)) < 0) {
                help = help >> 1;
            } else {
                res = add(res, help);
                helpA = minus(helpA, multi(helpB, help));
                help = help >> 1;
            }
        }
        res = add(res, help);
        return res = (a < 0) ^ (b < 0) ? negNum(res) : res;
    }

    //for test
    public static void main(String[] args) {
        System.out.println(add(10, 29));
        System.out.println(minus(-10, 1));
        System.out.println(multi(-10, -100));
        System.out.println(div(10, 2));
        System.out.println(Integer.MIN_VALUE / 2);
    }

}
