package leetcode.median;

public class LC95JumpGame {
    //题目描述
    //给出一个非负整数数组，你最初在数组第一个元素的位置
    //
    //数组中的元素代表你在这个位置可以跳跃的最大长度
    //判断你是否能到达数组最后一个元素的位置
    //例如
    //A =[2,3,1,1,4], 返回 true.
    //
    //A =[3,2,1,0,4], 返回 false.

    public class Solution {
        /**
         * @param A int整型一维数组
         * @return bool布尔型
         */
        public boolean canJump(int[] A) {
            // write code here
            //right表示当前所能够跳到的最右边界
            //next表示下一次跳能够跳的最远位置
            //数组长度为1不用跳
            if (A.length == 1) {
                return true;
            }
            int right = 0;
            int next = A[0];
            for (int i = 0; i < A.length; i++) {
                if (i > right) {
                    //如果下一个能跳的最远位置和当前的最右位置是一样的,说明再也跳不动了
                    //一定要结合A.length==1,否则长度为1的数组直接出循环判断错误
                    //能进到这里说明i位置合法且需要移动
                    if (right == next) {
                        return false;
                    }
                    right = next;
                    if (right >= A.length - 1) {
                        return true;
                    }
                }
                next = Math.max(next, i + A[i]);
            }
            return false;
        }
    }
}
