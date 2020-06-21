package com.newcoder.zuo3.basic.class07;

public class Hanoi {
    //打印n层汉诺塔从最左边移动到最右边的全部过程
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }

    }

    //给定n层汉诺塔,打印将n层汉诺塔从from转到to使用mid作为辅助的流程
    public static void func(int n, String from, String to, String mid) {
        if (n == 1) {
            System.out.println("move from: " + from + " to " + to);
        } else {

            func(n - 1, from, mid, to);
            System.out.println("move from: " + from + " to " + to);
            func(n - 1, mid, to, from);
        }
    }


    public static void main(String[] args) {
        int n = 3;
        hanoi(n);
    }
}
