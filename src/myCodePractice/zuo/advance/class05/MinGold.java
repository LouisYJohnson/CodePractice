package myCodePractice.zuo.advance.class05;

import java.util.Arrays;

public class MinGold {
    //你的王国里有n条龙， 你希望雇佣一些勇士把它们杀死， 王国里一共有m个骑士
    //可以雇佣。 假定， 一个能力值 为x的骑士可以打败战斗力不超过x的恶龙， 且
    //需要支付x个金币。 已知勇士可以重复雇佣， 且重复雇佣需要重 复支付金币，
    //请求出打败所有的恶龙需要的最小金币数目。 例如， 你的王国里有三条龙，
    //战斗力分别为10， 11， 20， 同时有三个勇士可以雇佣， 能力值分别为
    //20,12,30， 最省钱的方式是能力值12的勇士攻击战斗力10的龙， 能力值12的勇
    //士攻击战斗力11的龙， 能力值 20的勇士攻击战斗力20的龙， 总共付出44金币。
    //进阶：
    //一条龙可以被勇士合力杀死， 求付出的金币数
    //举例：
    //int[] knights = { 2, 10, 5 };
    //int[] dragons = { 3, 8, 6 };
    //原问题标准下应返回： 25
    //进阶的标准下应返回： 22

    //非进阶:将骑士排序成有序数组,每次来一条龙,去骑士中二分查找(二分查找只能查找有序数组)刚好大于等于这个数的骑士,就是最省的
    public static int minGold1(int[] knights, int[] dragons) {
        if (knights == null || knights.length == 0 || dragons.length == 0 || dragons == null) return -1;
        int res = 0;
        int tempKnight = 0;
        //表示二分查找时的中间位置,左边界,右边界
        int mid = 0;
        int l = 0;
        int r = 0;
        //默认按照升序排序
        Arrays.sort(knights);
        //二分查找如果找不到,最终的结局都是l>r
        for (int i = 0; i < dragons.length; i++) {
            l = 0;
            r = knights.length - 1;
            mid = l + (r - l) / 2;
            tempKnight = 0;
            while (l <= r) {
                //如果碰到了满足条件的骑士,看能不能找到更小的满足条件的骑士
                if (knights[mid] >= dragons[i]) {
                    tempKnight = knights[mid];
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
                mid = l + (r - l) / 2;
            }
            //说明没有找到能够打败龙的骑士,gg
            if (tempKnight == 0) {
                return -1;
            }
            res += tempKnight;
        }
        return res;
    }

    //进阶:背包问题,矩阵压缩
    //用左侧已有的几个数是否能加出从0到左侧数最大和的那么一张表,在6表示用3,6一起能搞定那些横着的数,
    // 所以如果 一个位置如果是true,下面的行的同一列都是true
    //所以dp[i][j]的判断条件为dp[i-1][j] || dp[i-1][j-arr[i]]且不越界,中一个为true就是true
    //arr[i]是竖排的数字
    //解决的是竖排数字随便选,看能不能加出横排的数字(竖排数字只能选择一次)
    //然后根据最后一行的情况变成右上角的表格,搞得定的数就填数字,搞不定的就填无穷,
    // 然后根据题意,有数的位置左侧填同一个数直到有新的数出现,就是无穷的位置向右找离得最近的不是无穷的数
    public static int minGold2(int[] knights, int[] dragons) {
        int knightsSum = 0;
        for (int i = 0; i < knights.length; i++) {
            knightsSum += knights[i];
        }
        boolean[][] dp = new boolean[knights.length][knightsSum + 1];
        dp[0][0] = true;
        dp[0][knights[0]] = true;

        for (int row = 1; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                if (dp[row - 1][col] || (col - knights[row] >= 0 && dp[row][col - knights[row]])) {
                    dp[row][col] = true;
                }else {
                    dp[row][col] = false;
                }
            }
        }
        //放true的说明能搞定,放false的说明搞不定
        //要的只是最后一行的结果,从右到左遇到true的时候数字变为与下标col相同,否则重复填当前数字
        int[] result = new int[dp[0].length];
        int help = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            if (dp[dp.length - 1][i]) {
                help = i;
            }
            result[i] = help;
        }

        int res = 0;
        //让龙来,对应他们的骑士
        for (int i = 0; i < dragons.length; i++) {
            res += result[dragons[i]];
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
