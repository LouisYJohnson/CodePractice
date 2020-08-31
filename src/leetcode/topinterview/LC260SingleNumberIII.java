package leetcode.topinterview;

public class LC260SingleNumberIII {
    //给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
    //
    //示例 :
    //
    //输入: [1,2,1,3,2,5]
    //输出: [3,5]
    //注意：
    //
    //结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
    //你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/single-number-iii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    /**
     * {@link LC137SingleNumberII}
     */
    class Solution {
        public int[] singleNumber(int[] nums) {
            //设题目中这两个只出现1次的数字分别为A和B，
            // 如果能将A，B分开到二个数组中，那显然符合“异或”解法的关键点了。
            // 因此这个题目的关键点就是将A，B分开到二个数组中。由于A，B肯定是不相等的，
            // 因此在二进制上必定有一位是不同的。根据这一位是0还是1可以将A，B分开到A组和B组。
            // 而这个数组中其它数字要么就属于A组，要么就属于B组。
            // 再对A组和B组分别执行“异或”解法就可以得到A，B了。
            // 而要判断A，B在哪一位上不相同，只要根据A异或B的结果就可以知道了，
            // 这个结果在二进制上为1的位就说明A，B在这一位上是不相同的。
            //作者：BugFreezrr
            //链接：https://leetcode-cn.com/problems/single-number-iii/solution/java-yi-huo-100-yao-shi-kan-bu-dong-wo-jiu-qu-chu-/
            //来源：力扣（LeetCode）
            //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

            //首先,将数组整体进行异或,得到的结果就是这两个只出现一次的数字的异或结果
            //然后根据这个结果第一个为1(也就是第一个不同的位)作为一个标准来将这个数组整体分为两部分
            //然后对这两个部分分别进行异或运算,就能得到结果(出现两次或者偶数次的元素进行异或运算就可以变成0,如果是奇数次的数组,就要改成LC137中的方法了)

            //首先将数组整体进行异或
            int[] res = new int[2];
            int num1XorNum2 = 0;
            for (int num : nums) {
                num1XorNum2 ^= num;
            }
            //找到异或结果中第一个为1的位置
            int tempMoveBits = 0;   //用来记录当前向右移动的bit位
            while (num1XorNum2 != 0) {
                if ((num1XorNum2 & 1) == 1) {
                    break;
                }
                tempMoveBits++;
                num1XorNum2 = num1XorNum2 >> 1;
            }
            //以这个1为标准,与上数组中每一个数,看对应的位置上是否为1,以这个为标准来判断属于哪个组
            //这里认为规定如果是1就属于num1,如果是0就属于num2
            int num1 = 0;
            int num2 = 0;
            int helpFlag = 1 << tempMoveBits;
            //以标志位为准,在遍历数组的同时将数组分为两个部分分别异或
            for (int num : nums) {
                //注意,这里不能是 == 1,因为这个这个位置不一定是在哪个bit位,所以要用!=0才对
                if ((helpFlag & num) != 0) {
                    num1 ^= num;
                }else {
                    num2 ^= num;
                }
            }

            res[0] = num1;
            res[1] = num2;

            return res;
        }
    }
}
