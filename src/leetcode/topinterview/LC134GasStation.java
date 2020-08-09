package leetcode.topinterview;

public class LC134GasStation {
    //在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
    //你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
    // 你从其中的一个加油站出发，开始时油箱为空。
    //如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
    //说明: 
    //如果题目有解，该答案即为唯一答案。
    //输入数组均为非空数组，且长度相同。
    //输入数组中的元素均为非负数。
    //示例 1:
    //输入:
    //gas  = [1,2,3,4,5]
    //cost = [3,4,5,1,2]
    //输出: 3
    //解释:
    //从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
    //开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
    //开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
    //开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
    //开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
    //开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
    //因此，3 可为起始索引。
    //示例 2:
    //输入:
    //gas  = [2,3,4]
    //cost = [3,4,3]
    //输出: -1
    //解释:
    //你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
    //我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
    //开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
    //开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
    //你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
    //因此，无论怎样，你都不可能绕环路行驶一周。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/gas-station
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //https://leetcode-cn.com/problems/gas-station/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--30/
        public int canCompleteCircuit(int[] gas, int[] cost) {
            //这里说的环绕一周,指的是从哪出发就正好回到哪,比如从0位置出发,要能正好到达0
            for (int gasPos = 0; gasPos < gas.length; gasPos++) {
                int gasRemain = gas[gasPos];
                int curPos = gasPos;
                while (gasRemain - cost[curPos] >= 0) { //能够前进,才能加上油
                    //前进的坐标需要取模,因为这是一个圈
                    gasRemain = gasRemain - cost[curPos] + gas[(curPos + 1) % gas.length];
                    //加油之后,更新目前所在位置
                    //如果这个位置正好是环绕一周的位置,直接返回
                    curPos = (curPos + 1) % gas.length;
                    if (curPos == gasPos) {
                        return gasPos;
                    }
                }
                if (curPos < gasPos) { //绕了一圈回到了前面,还到不了当前位置,直接为-1
                    return -1;
                }
                //如果从gasPos走到的最远位置还不能到自身(没绕回来),
                // 那么这些沿途上的加油站都不能满足环绕一圈的要求,直接从最远位置+1开始走即可(进入下一次循环前gasPos++)
                gasPos = curPos;
            }
            return -1;
        }
    }
}
