package com.newcoder.zuo3.advanced.class05;

import java.util.Arrays;

public class Code_03_Min_Gold {
    //你的王国里有n条龙， 你希望雇佣一些勇士把它们杀死， 王国里一共有m个骑士
    //可以雇佣。 假定， 一个能力值 为x的骑士可以打败战斗力不超过x的恶龙， 且
    //需要支付x个金币。 已知勇士可以重复雇佣， 且重复雇佣需要重 复支付金币，
    //请求出打败所有的恶龙需要的最小金币数目。 例如， 你的王国里有三条龙，
    //战斗力分别为10， 11， 20， 同时有三个勇士可以雇佣， 能力值分别为
    //20,12,30， 最省钱的方式是能力值12的勇士攻击战斗力10的龙， 能力值12的勇
    //士攻击战斗力11的龙， 能力值 20的勇士攻击战斗力20的龙， 总共付出44金币。
    //非进阶:将骑士排序成有序数组,每次来一条龙,去骑士中二分查找刚好大于等于这个数的骑士,就是最省的
    //进阶：
    //一条龙可以被勇士合力杀死， 求付出的金币数
    //举例：
    //int[] knights = { 2, 10, 5 };
    //int[] dragons = { 3, 8, 6 };
    //原问题标准下应返回： 25
    //进阶的标准下应返回： 22
    //进阶:背包问题,矩阵压缩

    //非进阶:
    public static int minGold1(int[] knights, int[] dragons) {
        Arrays.sort(knights);
        int res = 0;
        for (int i = 0; i < dragons.length; i++) {
            int cost = findDragonSlayer(knights, dragons[i]);
            if (cost == Integer.MAX_VALUE) return Integer.MAX_VALUE;
            res += cost;
        }
        return res;
    }

    //使用二分查找,找到一条龙对应的屠龙者(能力值刚好大于或者等于这条龙能力值的骑士)
    public static int findDragonSlayer(int[] knightsSorted, int dragon) {
        int l = 0;
        int r = knightsSorted.length - 1;
        int index = -1;
        //二分查找法中的最终都会变成r指针在l指针的左侧,这就是结束的条件
        //二分查找可以找一个刚好小于或者大于一个数的已排序数组中的数
        //比如要在已排序数组中找刚好大于num的数,
        // 只要在mid对应的数大于等于num的时候更新r并保存此时的mid坐标(为了找是否有更小的且大于num的数)
        //否则,只更新l(因为这个时候mid对应的数小于num,所以需要mid对应更大的数,只有更新l的操作能做到这一点)
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (knightsSorted[mid] < dragon) {
                l = mid + 1;
            } else {
                index = mid;
                r = mid - 1;
            }
        }
        return index == -1 ? Integer.MAX_VALUE : knightsSorted[index];
    }

    //进阶:背包问题,矩阵压缩
    //用左侧已有的几个数是否能加出从0到左侧数最大和的那么一张表,在6表示用3,6一起能搞定那些横着的数,所以如果 一个位置如果是true,下面的行的同一列都是true
    //所以dp[i][j]的判断条件为dp[i-1][j] || dp[i-1][j-arr[i]]且不越界,中一个为true就是true
    //arr[i]是竖排的数字
    //解决的是竖排数字随便选,看能不能加出横排的数字(竖排数字只能选择一次)
    //然后根据最后一行的情况变成右上角的表格,搞得定的数就填数字,搞不定的就填无穷
    // ,然后根据题意,有数的位置左侧填同一个数直到有新的数出现,就是无穷的位置向右找离得最近的不是无穷的数
    public static int minGold2(int[] knights, int[] dragons) {
        int sumDragon = 0;
        for (int i = 0; i < dragons.length; i++) {
            sumDragon += dragons[i];
        }
        boolean[][] dp = new boolean[knights.length][sumDragon + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (i == 0 && (j == knights[i])) {
                    dp[i][j] = true;
                } else if (i != 0 && (j - knights[i] >= 0)) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - knights[i]];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        int[] help = new int[sumDragon + 1];
        int helpInt = 0;
        for (int i = help.length - 1; i >= 0; i--) {
            if (dp[dp.length - 1][i]) {
                helpInt = i;
                help[i] = i;
            } else {
                help[i] = helpInt;
            }
        }
        int res = 0;
        for (int i = 0; i < dragons.length; i++) {
            res += help[dragons[i]];
        }
        return res;
    }


    //for test
    public static void main(String[] args) {
        int[] knights1 = {2, 10, 5};
        int[] dragons1 = {3, 8, 6};
        System.out.println(minGold1(knights1, dragons1));

        int[] knights2 = {2, 10, 5};
        int[] dragons2 = {3, 8, 6};
        System.out.println(minGold2(knights2, dragons2));

    }
}
