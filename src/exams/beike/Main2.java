package exams.beike;

import java.util.Scanner;

public class Main2 {
    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String s = sc.next();
        if (len == 0 || s == null || s.length() == 0 || (s.length() != len)) {
            System.out.println(0);
            return;
        }
        sc.close();
        process1(s, 0, 0, new StringBuilder(), false);
        if (res == Integer.MAX_VALUE) {
            System.out.println(0);
            return;
        }
        System.out.println(res);
    }

    //回溯法
    private static void process1(String s, int i, int num, StringBuilder helpSB, boolean flag) {
        if (i == s.length()) {
            if (helpSB.toString().equals(s)) {
                res = Math.min(res, num);
            }
            return;
        }

        if (i > s.length()) {
            return;
        }

        //两种选择,要么添加任意字符,要么复制之前的字符
        //添加任意字符
//        for (char j = 'a'; j <= 'z'; j++) {
//            if (s.charAt(i) != j) {
//                continue;
//            }
//            helpSB.append(j);
//            process1(s, i + 1, num + 1, helpSB, flag);
//            helpSB.deleteCharAt(helpSB.length() - 1);
//        }
//        for (char j = '0'; j <= '9'; j++) {
//            if (s.charAt(i) != j) {
//                continue;
//            }
//            helpSB.append(j);
//            process1(s, i + 1, num + 1, helpSB, flag);
//            helpSB.deleteCharAt(helpSB.length() - 1);
//        }
//        for (char j = 'A'; j <= 'Z'; j++) {
//            if (s.charAt(i) != j) {
//                continue;
//            }
//            helpSB.append(j);
//            process1(s, i + 1, num + 1, helpSB, flag);
//            helpSB.deleteCharAt(helpSB.length() - 1);
//        }
        for (int j = 0; j <= 65536; j++) {
            char tempChar = (char) j;
            if (s.charAt(i) != tempChar) {
                continue;
            }
            helpSB.append(tempChar);
            process1(s, i + 1, num + 1, helpSB, flag);
            helpSB.deleteCharAt(helpSB.length() - 1);
        }
        //复制之前字符,只能使用一次,所以分别尝试不复制之前字符进入下一层与复制之前字符进入下一层
        int tempSize = helpSB.length();
        //如果当前还没有使用过复制,尝试使用或者不使用
        if (!flag) {
            if (tempSize != 0) {
                helpSB.append(helpSB);
                flag = true;
                process1(s, i + tempSize, num + 1, helpSB, flag);
                flag = false;
                helpSB.delete(tempSize, helpSB.length());
            }
        }
    }


    //递归函数功能:
    //  给定一个字符串与当前所在位置,
    //  当前位置没有整理好,前面的都整理好了
    //  返回最少需要的步骤数
//    private static int process(String s,int i) {
//        if (i == s.length()) {
//            if () {
//            }
//        }
//
//    }


}
