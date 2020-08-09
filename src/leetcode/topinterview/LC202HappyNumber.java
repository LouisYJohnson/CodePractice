package leetcode.topinterview;

public class LC202HappyNumber {
    //编写一个算法来判断一个数 n 是不是快乐数。
    //「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
    //如果 n 是快乐数就返回 True ；不是，则返回 False 。
    //示例：
    //输入：19
    //输出：true
    //解释：
    //12 + 92 = 82
    //82 + 22 = 68
    //62 + 82 = 100
    //12 + 02 + 02 = 1
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/happy-number
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        public boolean isHappy(int n) {
            //其实就是判断链表是否有环
            //我们使用快慢指针法:
            //快慢指针法:
            //慢指针每次走一个,快指针每次走两个
            //如果快指针走到了null,则无环,否则快慢指针一定能相遇,
            // 此时让快指针回到头节点,快慢节点每次走一个,一定能在入环处相遇
            //本题如果无环,那一定能走到1,如果有环,二者一定会相等
            if (n == 0) {
                return false;
            }

            int slow = n;
            int fast = n;
            while (fast != 1) {
                fast = getNext(getNext(fast));
                slow = getNext(slow);
                if (fast == slow && fast != 1) {    //如果更新后相同了,但是不是1,那就是false,说明有环
                    //如果这里不判断fast!=1的话,只判断fast==slow,是会出错的,比如10,快慢指针只要一动就合格了
                    return false;
                }
            }
            //到这里,说明出环了
            return true;
        }

        public int getNext(int n) {
            int res = 0;
            while (n != 0) {
                res += Math.pow(n % 10, 2);
                n /= 10;
            }
            return res;
        }
    }
}
