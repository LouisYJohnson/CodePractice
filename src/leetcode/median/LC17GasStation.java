package leetcode.median;

public class LC17GasStation {
    //题目描述
    //环形路上有n个加油站，第i个加油站的汽油量是gas[i].
    //你有一辆车，车的油箱可以无限装汽油。从加油站i走到下一个加油站（i+1）花费的油量是cost[i]，你从一个加油站出发，刚开始的时候油箱里面没有汽油。
    //求从哪个加油站出发可以在环形路上走一圈。返回加油站的下标，如果没有答案的话返回-1。
    //注意：
    //答案保证唯一。
    //
    //There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
    //You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
    //
    //Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
    //
    //Note:
    //The solution is guaranteed to be unique.

    public class Solution {
        /**
         *
         * @param gas int整型一维数组
         * @param cost int整型一维数组
         * @return int整型
         */
        public int canCompleteCircuit (int[] gas, int[] cost) {
            // write code here
            //从start出发， 如果油量足够， 可以一直向后走 end++；  油量不够的时候，
            //start向后退  最终 start == end的时候，如果有解一定是当前 start所在位置
            //TODO https://www.nowcoder.com/profile/9655610/codeBookDetail?submissionId=16355593
            return -1;
        }
    }
}
