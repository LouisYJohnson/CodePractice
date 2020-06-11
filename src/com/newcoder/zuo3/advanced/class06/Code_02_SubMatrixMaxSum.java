package com.newcoder.zuo3.advanced.class06;

import java.util.Arrays;

public class Code_02_SubMatrixMaxSum {
    //�Ӿ��������ۼӺ�����
    //����Ŀ��
    //����һ������matrix�� ���е�ֵ������ �и��� ��0�� �����Ӿ��������ۼӺ͡�
    //���磬 ����matrixΪ��
    //-90 48 78
    //64 -40 64
    //-81 -7 66
    //���У� ����ۼӺ͵��Ӿ���Ϊ��
    //48 78
    //-40 64
    //-7 66
    //���Է����ۼӺ�209��
    //���磬 matrixΪ��
    //-1 -1 -1
    //-1 2 2
    //-1 -1 -1
    //���У� ����ۼӺ͵��Ӿ���Ϊ��
    //2 2
    //���Է����ۼӺ�4��

    //���԰Ѷ��е�����ת����һ��
    //ÿ�ζ��Ѷ���ѹ����һ������,�������������ۼӺ�,����������о����Ӧ���Ӿ��������ۼӺ�
    //
    //ÿ���� 1,1 2,1 2 3,1 2 3 4
    //2,2 3,2 3 4
    //3,3 4
    //4
    //Ȼ�������ֵ���ǽ��
    //
    //�����Ӿ���Ĺ��̱����������������Ϸ�ʽ�Ĺ���
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int helpMaxValueSize = 0;
        for (int i = 0; i < m.length; i++) {
            helpMaxValueSize += (i + 1);
        }
        int[] helpMaxValue = new int[helpMaxValueSize];
//        int[] helpSubSum = new int[m[0].length];
        int index = 0;
        //��������������Ƕ��ѭ��,�ҵ��������������е����(���������4�дӵ�һ�п�ʼ��2,3,4,Ȼ���2��ʼ��3,4,��3��ʼ��4,���ֻ��4�Լ�)
        for (int i = 0; i < m.length; i++) {            //��
            int[] helpSubSum = new int[m[0].length];
            for (int j = i; j < m.length; j++) {    //�������������
                for (int k = 0; k < m[0].length; k++) {
                    helpSubSum[k] += m[j][k];
                }
                helpMaxValue[index++] = maxSumArr(helpSubSum);
            }
        }
        Arrays.sort(helpMaxValue);
        return helpMaxValue[0];
    }

    public static int maxSumArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            if (cur > max) {
                max = cur;
            }
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));
    }
}
