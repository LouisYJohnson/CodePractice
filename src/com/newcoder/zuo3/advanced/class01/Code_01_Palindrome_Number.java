package com.newcoder.zuo3.advanced.class01;

public class Code_01_Palindrome_Number {
    //给定一个整数， 判断该数是否是回文数
    public static boolean isPalindrome(int n) {
        int help = 1;
        int num = n;
        //找到一个和n一样长的以1开头后面全是0的整数
        while (num/10 != 0) {
            help *= 10;
            num /= 10;
        }
        while (n != 0) {
            //去除高位:num-(num/help)*help
            //去除低位:num%10
            //如果高位!=低位,直接返回false
            if (n / help != n % 10) return false;
            //否则,更新n(删除高位低位),help,并继续下一步循环
            n = n - (n / help) * help;
            n /= 10;
            help /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        int temp = 1;
        System.out.println(isPalindrome(temp));
    }
}
