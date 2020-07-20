package leetcode.median;

public class LC82PlusOne {
    //题目描述
    //给出用数字数组表示的一个非负整数，请对该整数加1。
    public class Solution {
        /**
         *
         * @param digits int整型一维数组
         * @return int整型一维数组
         */
        public int[] plusOne (int[] digits) {
            // write code here
            //如果有进位,最多进一位
            //所以辅助数组比原来的数组长1
            int[] help = new int[digits.length + 1];
            int helpIndex= digits.length - 1;
            //从右到左初始化数组,将原始数组复制进去,没有元素的位置就用0代替
            for (int i = help.length - 1; i >= 0; i--) {
                if (helpIndex >= 0) {
                    help[i] = digits[helpIndex--];
                }else {
                    help[i] = 0;
                }
            }
            //进位标志,因为要+1所以从第0位(对应数组最后一个元素)开始就是认为有进位
            int flag = 1;
            for (int i = help.length - 1; i >= 0; i--) {
                if (help[i] == 9 && flag == 1) {    //如果是9还有进位,则变0,并将进位信息传递下去(进位信息不变)
                    help[i] = 0;
                } else if (help[i] != 9 && flag == 1) { //如果不是9还有进位,则将进位信息变为0,并将本位+1
                    help[i] = help[i] + 1;
                    flag = 0;
                }
                //其余情况,保持原状即可
            }
            //如果+1后发现最高位用来辅助进位的并没有,要把这个0从最高位上去掉
            if (help[0] == 0) {
                int[] res = new int[help.length - 1];
                int index = 0;
                for (int i = 1; i < help.length; i++) {
                    res[index++] = help[i];
                }
                return res;
            }else {
                return help;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new LC82PlusOne().new Solution();
        int[] res = solution.plusOne(new int[] {0});
        System.out.println(1);
    }
}
