package com.newcoder.zuo3.gaopin.class03;

public class Problem_04_SubMatrixMaxSum {
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

    //���԰Ѷ��е�����ת����һ��(�Ӿ���,��һ���������Ǳ�����ӵ�,����ѡ���ֻ�����ҵı߽�)
    //ÿ�ζ��Ѷ���ѹ����һ������,�������������ۼӺ�,����������о����Ӧ���Ӿ��������ۼӺ�
    //
    //ÿ���� 1,1 2,1 2 3,1 2 3 4
    //2,2 3,2 3 4
    //3,3 4
    //4
    //Ȼ�������ֵ���ǽ��
    //
    //�����Ӿ���Ĺ��̱����������������Ϸ�ʽ�Ĺ���

    public static int maxSum(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) return 0;

        int max = Integer.MIN_VALUE;
        //��ʼ��
        for (int k = 0; k < arr.length; k++) {
            int[] help = new int[arr[0].length];
            //������
            for (int i = k; i < arr.length; i++) {
                //��
                for (int j = 0; j < arr[0].length; j++) {
                    help[j] += arr[i][j];
                }
                max = Math.max(subArrayMaxSum(help), max);
            }
        }
        return max;
    }

    public static int subArrayMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = Math.max(0, cur);
        }
        return max;
    }

    //for test
    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));

    }


}
