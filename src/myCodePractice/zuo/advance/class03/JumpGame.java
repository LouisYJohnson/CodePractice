package myCodePractice.zuo.advance.class03;

public class JumpGame {
    //跳跃游戏
    //【题目】
    //给定数组arr， arr[i]==k代表可以从位置i向右跳1~k个距离。
    //比如， arr[2]==3， 代表从位置2可以跳到位置3、 位置4或位置5。
    //如果从位置0出发， 返回最少跳几次能跳到arr最后的位置上。
    //【举例】
    //arr=[3,2,3,1,1,4]。
    //arr[0]==3， 选择跳到位置2； arr[2]==3， 可以跳到最后的位置。
    //所以返回2。
    //【要求】
    //如果arr长度为N， 要求实现时间复杂度为O(N)、 额外空间复杂
    //度为O(1)的方法。

    //三个变量,k,cur,next,
    //k为跳了几次
    //cur为上一次跳的位置可以到达的最右边界(在这个范围内的点上一次所有候选点中,能够跳的最远的那个)
    //next为在当前位置如果要跳的话,能跳到的最右边界(候选点中每一个最远的能跳到哪个位置)
    //如果当前点已经在候选点之外,那么下一次的候选点就是到next位置的,也就是下一步跳的是所有候选点中最远的距离
    public static int jumpGame(int[] arr) {
        if (arr == null || arr.length == 0) return -1;

        int cur = 0;
        int next = 0;
        int k = 0;

        for (int i = 0; i < arr.length; i++) {
            //只有在当前位置在上一次跳跃的最大边界外,这一次才跳
            //并且这一跳能够跳的范围更新为所有候选点中最右的边界
            //也就是说,之后的位置只要能在这一跳能够到达范围的内部cur,就不用跳了
            if (i > cur) {
                k++;
                cur = next;
            }
            //每次都更新候选点中最远的距离
            next = Math.max(next, arr[i] + i);
        }
        return k;
    }
}
