package com.newcoder.zuo3.advanced.class02;

public class Code_01_Ugly_Number {
    public static int uglyNumber1(int index) {
        int start = 0;
        int count = 0;
        while (count < index) {
            start++;
            if (isUgly(start)) {
                count++;
            }
        }
        return start;
    }

    public static boolean isUgly(int num) {
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1 ? true : false;
    }

    public static int uglyNumber2(int n) {
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int[] help = new int[n];
        help[0] = 1;
        for (int index = 1; index < help.length; index++) {
            help[index] = Math.min(help[i2] * 2, Math.min(help[i3] * 3, help[i5] * 5));
            if (help[i2] * 2 == help[index]) i2++;
            if (help[i3] * 3 == help[index]) i3++;
            if (help[i5] * 5 == help[index]) i5++;
        }
        return help[n - 1];
    }

    //for Combinations
    public static void main(String[] args) {
        int test = 8;
        System.out.println(uglyNumber1(test));
        System.out.println(uglyNumber2(test));
    }

    //implement by zuo
//    public static int uglyNumber1(int index) {
//        int number = 0;
//        int count = 0;
//        while (count < index) {
//            ++number;
//            if (isUgly(number))
//                count++;
//        }
//        return number;
//    }
//
//    public static boolean isUgly(int number) {
//        while (number % 2 == 0)
//            number = number / 2;
//        while (number % 3 == 0)
//            number = number / 3;
//        while (number % 5 == 0)
//            number = number / 5;
//        return (number == 1) ? true : false;
//    }
//
//    public static int uglyNumber2(int n) {
//        int[] help = new int[n];
//        help[0] = 1;
//        int i2 = 0;
//        int i3 = 0;
//        int i5 = 0;
//        int index = 1;
//        while (index < n) {
//            help[index] = Math.min(2 * help[i2], Math.min(3 * help[i3], 5 * help[i5]));
//            if (help[index] == 2 * help[i2])
//                i2++;
//            if (help[index] == 3 * help[i3])
//                i3++;
//            if (help[index] == 5 * help[i5])
//                i5++;
//            index++;
//        }
//        return help[index - 1];
//    }
//
//    public static void main(String[] args) {
//        int Combinations = 8;
//        System.out.println(uglyNumber1(Combinations));
//        System.out.println(uglyNumber2(Combinations));
//    }

}
