package myCodePractice.zuo.basic.class07;

import java.util.HashSet;

public class PrintAllPermutations {
    //打印一个字符串的全部排列
    //前面的都排好了,当前位置选择剩下的字符放到当前位置上
    //如何选择剩下的字符放到当前位置上?使用交换最好
    public static void prinAllPermutations(String str) {
        if (str == null || str.length() == 0) return;
        char[] chars = str.toCharArray();

        process1(chars, 0);
        System.out.println("============-===");
        process2(chars, 0);
    }

    //递归函数功能:
    //传入字符串数组与位置i,打印从0-i-1位置开始已经排好的字符串
    public static void process1(char[] chars, int i) {
        //base case
        //如果已经到了结尾,直接打印当前得到的字符串就可以了
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }

        //i位置和i到length-1位置上的挨个位置上遍历一遍并交换
        //交换结束记得换回来,引用类型数据在递归函数中的改动是全局的,会乱
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            process1(chars, i + 1);
            swap(chars, i, j);
        }
    }


    //打印一个字符串的全部排列， 要求不要出现重复的排列
    //使用一个Set对同一个位置出现的字符进行限制,如果同一个位置上的字符重复出现了,不要
    public static void process2(char[] chars, int i) {
        //base case
        //如果已经到了结尾,直接打印chars
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        HashSet<Character> helpSet = new HashSet<>();
        for (int j = i; j < chars.length; j++) {
            if (!helpSet.contains(chars[j])) {
                helpSet.add(chars[j]);
                swap(chars, i, j);
                process2(chars, i + 1);
                swap(chars, i, j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String str = "acc";
        prinAllPermutations(str);
    }
}
