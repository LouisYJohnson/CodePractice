package com.newcoder.zuo3.basic.class07;

public class Print_All_Subsquences {
    //    打印一个字符串的全部子序列， 包括空字符串
    //流程:当前位置要或者不要,当前位置之前的已经确定 ,当前位置之后的还没有确定
    //大流程可以分解为两个小流程(当前位置要,不要),并且这些小流程与大流程逻辑完全一致
    public static void printAllSubsquence(String str) {
        char[] chars = str.toCharArray();
        process(chars, 0);

    }

    //    public static void process(char[] chs, int i) {
//        if (i == chs.length) {
//            System.out.println(String.valueOf(chs));
//            return;
//        }
//        //当前位置要
//        process(chs,i+1);
//        //当前位置不要(操作的是引用数据类型,所以要创建一个新的,或者在流程结束后修改回来)
//        char[] tempChs = chs.clone();
//        tempChs[i] = 0;
//        process(tempChs,i+1);
//    }
    public static void process(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process(chs, i + 1);
        char tmp = chs[i];
        chs[i] = 0;
        process(chs, i + 1);
        chs[i] = tmp;
    }

    public static void main(String[] args) {
        String test = "abc";
        printAllSubsquence(test);
    }
}
