package leetcode.topinterview;


public class LC137SingleNumberII {
    //给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
    //
    //说明：
    //
    //你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
    //
    //示例 1:
    //
    //输入: [2,2,3,2]
    //输出: 3
    //示例 2:
    //
    //输入: [0,1,0,1,0,1,99]
    //输出: 99
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/single-number-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * {@link LC260SingleNumberIII}
     */
    class Solution {
        public int singleNumber(int[] nums) {
            //https://leetcode-cn.com/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-wei-yun-suan-ke-yi-tu/
            //此方法可以推广到其他数出现n次,找出现一次的数
            //对一个二进制位,遍历数组中所有的数字,并将当前二进制位%n,就是这个二进制位上只出现一次数字到底有没有1
            //最外层,对32个整数位进行遍历
            int res = 0;
            for (int i = 0; i < 32; i++) {
                int curBits = 0;
                //内层,对所有数字进行遍历,计算同一个位上1的个数,肯定是3或者3+1或者0
                for (int num : nums) {
                    int curNum = num >> i;
                    curBits += (curNum & 1) == 1 ? 1 : 0;
                }
                curBits %= 3;
                //这里不能写成res += curBits * (int) Math.pow(2, i);
                //会越界,int的范围为2^31 - 1到-2^31
                //而这里的操作是把当前位置的这个1推到他应该在的地方
                //对于涉及到位运算的,因为涉及到正负数存储方式的问题,所以还是这种方法比较稳妥
                res += curBits << i;
            }
            return res;
        }
    }
}
