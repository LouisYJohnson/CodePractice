package com.newcoder.zuo3.basic.class07;

import java.util.HashSet;

public class Print_All_Permutations {
    //1.打印一个字符串的全部排列
    //2.打印一个字符串的全部排列， 要求不要出现重复的排列

    //1.打印一个字符串的全部排列
    public static void printAllPermutations1(String str) {
        char[] chars = str.toCharArray();
        process1(chars,0);

    }
    //i位置表示,0~i-1位置的字符都已经组合好了,而i位置的字符是由i~N-1位置的字符自由组合而来
    public static void process1(char[] chs, int i) {
        //base case:
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        for (int j = i; j < chs.length; j++) {
            //从i~N-1位置的字符选一个放到i的位置上,但是不能改变剩余字符,交换是最好的方法
            swap(chs,i,j);
            //继续逻辑相同的子过程
            process1(chs,i+1);
            //子过程完毕后,要把换过去的字符换回来,如果不换回来,就乱了
            swap(chs,i,j);
        }
    }

    //2.打印一个字符串的全部排列， 要求不要出现重复的排列
    public static void printAllPermutations2(String str) {
        char[] chars = str.toCharArray();
        process2(chars,0);
    }
    //逻辑和重复的是一样的,只是多了一个HashSet防止重复,如果重复就不打印而已
    public static void process2(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        HashSet<Character> hashSet = new HashSet<Character>();
        for (int j = i; j < chs.length; j++) {
            //如果已经换过同样的了,就不换了
            if (!hashSet.contains(chs[j])) {
                hashSet.add(chs[j]);
                swap(chs,i,j);
                process2(chs,i+1);
                swap(chs,i,j);
            }
        }
    }

    public static void swap(char[] arr,int i,int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String test1 = "abc";
        printAllPermutations1(test1);
        System.out.println("======");
        printAllPermutations2(test1);
        System.out.println("======");

        String test2 = "acc";
        printAllPermutations1(test2);
        System.out.println("======");
        printAllPermutations2(test2);
        System.out.println("======");
    }
}
