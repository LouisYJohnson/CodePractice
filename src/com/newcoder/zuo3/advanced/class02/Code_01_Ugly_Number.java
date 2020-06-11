package com.newcoder.zuo3.advanced.class02;

public class Code_01_Ugly_Number {
//    规定1是丑数， 其他的数如果只含有2或3或5的因子， 那么这个
//    数也是丑数。
//    比如依次的丑数为： 1,2,3,5,6,8,9,10,12,15...
//    求第n个丑数
//    笨办法:首先除2，直到不能整除为止，然后除5到不能整除为止，然后除3直到不能整除为止。最终判断剩余的数字是否为1，如果是1则为丑数，否则不是丑数。
//    快速办法:第i个丑数一定可以由前面的丑数或*2,*3,*5得到
//    做法:三个指针,分别指向当前数*2.*3.*5,每次找下一个数都是三个指针乘指向的数中最小的那一个,每个指针和当前数乘完后向前移一个
//    如果有多个指针和当前指向数字相乘后都相等而且还都是最小值,那么这些指针在更新当前值后一起向后移动
    //快速办法其实是一种可以解决很多问题的思路:
    //给一个符合一定规律的数列,让求这个数列的第n个数:找这个数列的规律看这第n个数由哪些数可以得到

    //笨办法:首先除2，直到不能整除为止，然后除5到不能整除为止，然后除3直到不能整除为止。最终判断剩余的数字是否为1，如果是1则为丑数，否则不是丑数。
    public static int uglyNumber1(int index) {
        //从0开始每一个数都这么做,如果是丑数,numOfUgly++表示找到一个丑数,直到numOfUgly == index为止
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

//    快速办法:第i个丑数一定可以由前面的丑数或*2,*3,*5得到
//    做法:三个指针,分别指向当前数*2.*3.*5,每次找下一个数都是三个指针乘指向的数中最小的那一个,每个指针和当前数乘完后向前移一个
//    如果有多个指针和当前指向数字相乘后都相等而且还都是最小值,那么这些指针在更新当前值后一起向后移动
    public static int uglyNumber2(int n) {
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        //help中装的是每一个丑数,但是下标从0开始,所以拿第n个丑数是拿help[n-1]位置上的丑数
        int[] help = new int[n];
        help[0] = 1;
        //从头开始,填满这个help数组
        for (int index = 1; index < help.length; index++) {
            //help数组中新加入的这个数字一定是之前得到的最小的
            help[index] = Math.min(help[i2] * 2,Math.min(help[i3] * 3,help[i5] * 5));
            //如果有多个指针和当前指向数字相乘后都相等而且还都是最小值的话,那么这些指针应该在更新当前值后一起++
            if (help[i2] * 2 == help[index]) i2++;
            if (help[i3] * 3 == help[index]) i3++;
            if (help[i5] * 5 == help[index]) i5++;
        }
        return help[n - 1];
    }
    //for test
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
//        int test = 8;
//        System.out.println(uglyNumber1(test));
//        System.out.println(uglyNumber2(test));
//    }

}
