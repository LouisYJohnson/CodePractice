package leetcode.median;

public class LC80Sqrtx {
    //题目描述
    //实现函数 int sqrt(int x).
    //计算并返回x的平方根

    public class Solution {
        /**
         *
         * @param x int整型
         * @return int整型
         */
        public int sqrt (int x) {
            // write code here
            if (x == 0) return 0;
            //使用二分法查找这个数的平方根,必须用long类型,否则会越界
            long left = 0;
            long right = x;
            long mid;

            while (left <= right) {
                mid = left + (right - left) / 2;
                //||后面的条件表示四舍五入只能选小的
                if (mid * mid == x || (mid * mid < x && (mid + 1) * (mid + 1) > x)) {
                    return (int) mid;
                } else if (mid * mid < x) {
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            return 0;
        }
    }
}
