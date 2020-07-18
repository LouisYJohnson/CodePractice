package leetcode.median;

public class LC105JumpGameII {
    //题目描述
    //给出一个非负整数数组，你最初在数组第一个元素的位置
    //数组中的元素代表你在这个位置可以跳跃的最大长度
    //你的目标是用最少的跳跃次数来到达数组的最后一个元素的位置
    //例如
    //给出数组 A =[2,3,1,1,4]
    //
    //最少需要两次才能跳跃到数组最后一个元素的位置。
    // （从数组下标为0的位置跳长度1到达下标1的位置，然后跳长度3到数组最后一个元素的位置）
    public class Solution {
        /**
         * @param A int整型一维数组
         * @return int整型
         */
        public int jump(int[] A) {
            // write code here
            //引入变量,分别记录当前跳跃的次数,当前能够到达的最右边界,以及候选点的范围
            int k = 0;
            int right = 0;
            int next = A[0];

            for (int i = 0; i < A.length; i++) {
                //如果当前的位置大于最右边界,这个时候才动
                if (i > right) {
                    k++;
                    right = next;
                    //如果跳完了发现合格了,直接跳出循环
                    //其实不用,因为上面的循环还会进行,只是不会更新k了,这么写会少几次循环的执行
                    if (right >= A.length - 1) {
                        break;
                    }
                }
                //不管动与不动,都要更新候选点中的能到达的下一个最右边界
                //下一次跳的,就是从这些候选点中能够得到的最远的下一个最右边界
                next = Math.max(next, i + A[i]);
            }
            return k;
        }
    }
}
