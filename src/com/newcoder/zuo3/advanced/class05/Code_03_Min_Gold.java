package com.newcoder.zuo3.advanced.class05;

import java.util.Arrays;

public class Code_03_Min_Gold {
    //�����������n������ ��ϣ����ӶһЩ��ʿ������ɱ���� ������һ����m����ʿ
    //���Թ�Ӷ�� �ٶ��� һ������ֵ Ϊx����ʿ���Դ��ս����������x�Ķ����� ��
    //��Ҫ֧��x����ҡ� ��֪��ʿ�����ظ���Ӷ�� ���ظ���Ӷ��Ҫ�� ��֧����ң�
    //�����������еĶ�����Ҫ����С�����Ŀ�� ���磬 �������������������
    //ս�����ֱ�Ϊ10�� 11�� 20�� ͬʱ��������ʿ���Թ�Ӷ�� ����ֵ�ֱ�Ϊ
    //20,12,30�� ��ʡǮ�ķ�ʽ������ֵ12����ʿ����ս����10������ ����ֵ12����
    //ʿ����ս����11������ ����ֵ 20����ʿ����ս����20������ �ܹ�����44��ҡ�
    //�ǽ���:����ʿ�������������,ÿ����һ����,ȥ��ʿ�ж��ֲ��Ҹպô��ڵ������������ʿ,������ʡ��
    //���ף�
    //һ�������Ա���ʿ����ɱ���� �󸶳��Ľ����
    //������
    //int[] knights = { 2, 10, 5 };
    //int[] dragons = { 3, 8, 6 };
    //ԭ�����׼��Ӧ���أ� 25
    //���׵ı�׼��Ӧ���أ� 22
    //����:��������,����ѹ��

    //�ǽ���:
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

    //ʹ�ö��ֲ���,�ҵ�һ������Ӧ��������(����ֵ�պô��ڻ��ߵ�������������ֵ����ʿ)
    public static int findDragonSlayer(int[] knightsSorted, int dragon) {
        int l = 0;
        int r = knightsSorted.length - 1;
        int index = -1;
        //���ֲ��ҷ��е����ն�����rָ����lָ������,����ǽ���������
        //���ֲ��ҿ�����һ���պ�С�ڻ��ߴ���һ�����������������е���
        //����Ҫ���������������Ҹպô���num����,
        // ֻҪ��mid��Ӧ�������ڵ���num��ʱ�����r�������ʱ��mid����(Ϊ�����Ƿ��и�С���Ҵ���num����)
        //����,ֻ����l(��Ϊ���ʱ��mid��Ӧ����С��num,������Ҫmid��Ӧ�������,ֻ�и���l�Ĳ�����������һ��)
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

    //����:��������,����ѹ��
    //��������еļ������Ƿ��ܼӳ���0����������͵���ôһ�ű�,��6��ʾ��3,6һ���ܸ㶨��Щ���ŵ���,������� һ��λ�������true,������е�ͬһ�ж���true
    //����dp[i][j]���ж�����Ϊdp[i-1][j] || dp[i-1][j-arr[i]]�Ҳ�Խ��,��һ��Ϊtrue����true
    //arr[i]�����ŵ�����
    //������������������ѡ,���ܲ��ܼӳ����ŵ�����(��������ֻ��ѡ��һ��)
    //Ȼ��������һ�е����������Ͻǵı��,��ö�������������,�㲻���ľ�������
    // ,Ȼ���������,������λ�������ͬһ����ֱ�����µ�������,���������λ���������������Ĳ����������
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
