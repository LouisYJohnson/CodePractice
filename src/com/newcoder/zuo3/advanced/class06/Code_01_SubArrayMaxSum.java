package com.newcoder.zuo3.advanced.class06;

import java.util.Arrays;

public class Code_01_SubArrayMaxSum {
    //�����������ۼӺ�����
    //����Ŀ��
    //����һ������arr�� ���������������ۼӺ͡�
    //���磬 arr=[1,-2,3,5,-2,6,-1]�� ���е��������У� [3,5,-2,6]
    //�����ۼӳ����ĺ�12�� ���Է���12��
    //��Ҫ��
    //���arr����ΪN�� Ҫ��ʱ�临�Ӷ�ΪO(N)�� ����ռ临�Ӷ�Ϊ
    //O(1)��
    //��������Ŀ��
    //����һ������arr�� �������������������������ۼӺ͡�

    //Ҫ���е���Ŀ:
    //������һ�������,�����ۼӺ�������ôһ��������
    //cur��ʼֵΪ0,max��ʼֵΪϵͳ��С
    //��ͷ��������,cur�����ۼ�,
    // ���cur����max,��max����(���curһֱΪ��,�Ǿ����Ҹ����е����ֵ),
    // ���curС��max,�Ҵ���0,������max,һ��cur��ΪС��0��,��cur����Ϊ0(�൱����������鲻Ҫ��)
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int cur = 0;

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

    //����:
    //Ԥ��������,���ǰ�ÿ��λ���ϱ��������λ��Ϊ��β���߿�ʼ�����������������
    //Ҳ����˵,Ҫ������������,�ֱ��������λ�ÿ�ʼ,�������λ�ý�β��
    public static int maxSum2(int[] arr) {
        int[] helpLeft = new int[arr.length];
        int[] helpRight = new int[arr.length];
        int[] helpSum = new int[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            helpLeft[i] = maxSubSum1(arr, i);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            helpRight[i] = maxSubSum2(arr, i);
        }
        for (int i = 0; i < helpSum.length; i++) {
            helpSum[i] = helpLeft[i] + helpRight[i + 1];
        }
        Arrays.sort(helpSum);
        return helpSum[0];
    }

    public static int maxSubSum1(int[] arr, int index) {
        int max = Integer.MIN_VALUE;
        int cur = 0;

        for (int i = 0; i <= index; i++) {
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

    public static int maxSubSum2(int[] arr, int index) {
        int max = Integer.MIN_VALUE;
        int cur = 0;

        for (int i = index; i < arr.length; i++) {
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

    //for test
    public static int maxSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i != arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));
        System.out.println(maxSum1(arr1));
        System.out.println(maxSum2(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));
        System.out.println(maxSum1(arr2));
        System.out.println(maxSum2(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));
        System.out.println(maxSum1(arr3));
        System.out.println(maxSum2(arr3));

    }
}
