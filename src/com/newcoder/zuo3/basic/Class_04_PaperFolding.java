package com.newcoder.zuo3.basic;

public class Class_04_PaperFolding {
    //请把一段纸条竖着放在桌子上， 然后从纸条的下边向上方对折1次， 压出折痕后展开。 此时
    //折痕是凹下去的， 即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2
    //次， 压出折痕后展开， 此时有三条折痕， 从上到下依次是下折痕、 下折痕和上折痕。 给定一
    //个输入参数N， 代表纸条都从下边向上方连续对折N次， 请从上到下打印所有折痕的方向。
    //例如： N=1时， 打印：
    //down
    //N=2时， 打印：
    //down
    //down
    //up
    public static void printAllFolds(int N) {
        if (N == 0) return;
        printProcess(N,0,false);

    }

    public static void printProcess(int N,int i,boolean isUp) {
        if (i >= N) return;
        printProcess(N,i+1,false);
        System.out.println(isUp ? "up" : "down");
        printProcess(N,i+1,true);
    }

//    public static void main(String[] args) {
//        int N = 4;
//        printAllFolds(N);
//
//    }
//    public static void printAllFolds(int N) {
//        printProcess(1, N, true);
//    }
//
//    public static void printProcess(int i, int N, boolean down) {
//        if (i > N) {
//            return;
//        }
//        printProcess(i + 1, N, true);
//        System.out.println(down ? "down " : "up ");
//        printProcess(i + 1, N, false);
//    }
//
//    public static void main(String[] args) {
//        int N = 4;
//        printAllFolds(N);
//
//    }

}
