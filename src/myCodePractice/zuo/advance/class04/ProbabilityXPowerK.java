package myCodePractice.zuo.advance.class04;

public class ProbabilityXPowerK {
    //调整[0,x)区间上的数出现的概率
    //【题目】
    //假设函数Math.random()等概率随机返回一个在[0,1)范围上的
    //数， 那么我们知道， 在[0,x)区间上的数出现的概率为x
    //（0<x≤1） 。 给定一个大于0的整数k， 并且可以使用
    //Math.random()函数， 请实现一个函数依然返回在[0,1)范围上
    //的数， 但是在[0,x)区间上的数出现的概率为x^k(0<x≤1)。

    //进阶4 1 10:00
    //想变成x的几次幂就调用几次Math.random,选择最大的那个返回即可
    public static double probabilityXPowerK(int k) {
        if (k <= 0) return -1;
        double res = 0;
        for (int i = 0; i < k; i++) {
            res = Math.max(res, Math.random());
        }
        return res;
    }

    //for Combinations
    public static void main(String[] args) {
        double range = 0.5;
        int times = 5000000;
        int count = 0;
        for (int i = 0; i != times; i++) {
            if (probabilityXPowerK(2) < range) {
                count++;
            }
        }
        double p = (double) count / (double) times;
        System.out.println("range [0," + range + "), probability: " + p);
    }
}
