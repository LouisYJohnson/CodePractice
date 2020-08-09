package leetcode.topinterview;

public class LC277FindTheCelebrity {
    //假设你是一个专业的狗仔，参加了一个 n 人派对，
    // 其中每个人被从 0 到 n - 1 标号。
    // 在这个派对人群当中可能存在一位 “名人”。
    // 所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。
    //现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。
    // 而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，
    // 以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。
    //在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。
    //派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/find-the-celebrity
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    /* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

    public class Solution {
        public int findCelebrity(int n) {
            //从头开始找,这个人是否只认识一个人,如果是,遍历所有人,看是不是所有人都认识这个人
            //如果是,才是真名人
            //不是,就是假名人
            for (int i = 0; i < n; i++) {
                int iKnow = 0;
                for (int j = 0; j < n; j++) {
                    if (knows(i, j)) {
                        iKnow++;
                    }
                }
                if (iKnow == 1) {
                    int knowI = 0;
                    for (int j = 0; j < n; j++) {
                        if (knows(j, i)) {
                            knowI++;
                        }
                    }
                    if (knowI == n) {
                        return i;
                    }
                }
            }
            return -1;
        }

        //复杂度太高
        public int findCelebrity1(int n) {
            //构建两个数组,一个记录自己认识的人的个数
            //一个记录认识自己的人数
            //名人应该是自己认识的人只有一个,而认识自己的有n-1个
            int[] iKnow = new int[n];
            int[] knowI = new int[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (knows(i, j)) {
                        iKnow[i]++;
                    }
                    if (knows(j, i)) {
                        knowI[i]++;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (iKnow[i] == 1 && knowI[i] == n) {
                    return i;
                }
            }
            return -1;
        }

        public boolean knows(int i, int j) {
            return false;
        }
    }

}
