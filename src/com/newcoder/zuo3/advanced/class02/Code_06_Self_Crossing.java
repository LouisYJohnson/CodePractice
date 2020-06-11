package com.newcoder.zuo3.advanced.class02;

public class Code_06_Self_Crossing {
    //一个人开始在(0,0)， 总是第一次走向上方， 第二次走向左方， 第三次走向下方，
    //第四次走向右方， 第五次走向上方， 第六次走向左方， 第七次走向下方， 第八
    //次走向右方， 第九次走向上方...依次走下去。
    //给定一个数组arr， arr[i]表示第i次走的步数。
    //请返回， 按照数组的步数走， 会不会让走过的路径交叉在一起
    //由于普遍规律中需要最小的是i-5,所以需要先考虑到初始的可能,将初始可能都排除掉后,才开始看普遍的
    //先把初始情况规定好,后面普遍的才能不越界
    //后面的判断基于的都是当前的与过去的,而不是未来的
    public static boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4) return false;
        //初始情况
        if ((x[0] > x[2] && x[3] > x[1]) || (x[0] == x[2] && x[1] == x[3]) || (x[3] < x[1] && x[2] < x[4])) {
            return true;
        }
        //分情况
        for (int i = 5; i < x.length; i++) {
            if (    //i与i-5相交
                    ((x[i - 1] >= x[i - 3] - x[i - 5]) && (x[i - 1] < x[i - 3]) && x[i] + x[i - 4] >= x[i - 2])
                            ||//i与i-4相交
                            ((x[i - 1] == x[i - 3]) && (x[i] + x[i - 4] >= x[i - 2]))
                            ||//i与i-3相交
                            ((x[i - 1] < x[i - 3] - x[i - 5]) && (x[i] >= x[i - 2]))
            ) {
                return true;
            }
        }
        return false;
    }
}
