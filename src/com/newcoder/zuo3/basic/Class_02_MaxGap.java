package com.newcoder.zuo3.basic;

public class Class_02_MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int len = arr.length;
        //��������������Ƿ�ֻ��һ�������ظ�����
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        //ֻ��һ����,gapΪ0
        if (min == max) return 0;

        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];
        int numBucket = 0;
        //����������,������ӦͰ��״̬�ı�
        for (int i = 0; i < arr.length; i++) {
            numBucket = bucket(arr[i], len, min, max);
            //�����Ҳű�,û���Ļ�,�����С�Ķ���һ����ֵ
            maxs[numBucket] = hasNum[numBucket] ? Math.max(arr[i], maxs[numBucket]) : arr[i];
            mins[numBucket] = hasNum[numBucket] ? Math.min(arr[i], mins[numBucket]) : arr[i];
            hasNum[numBucket] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i < len + 1; i++) {
            if (hasNum[i]) {
                res = Math.max(mins[i] - lastMax, res);
                lastMax = maxs[i];
            }
        }
        return res;

    }


    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }
}
