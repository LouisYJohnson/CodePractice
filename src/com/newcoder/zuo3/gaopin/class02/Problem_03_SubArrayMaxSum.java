package com.newcoder.zuo3.gaopin.class02;

public class Problem_03_SubArrayMaxSum {
    //����һ������arr�����������������ۼӺ͡�
    //���磬a r r=[1��-2,3,5��-2,6��-1]�����е��������У�[3,5��-2,6]����
    //�ۼӳ����ĺ�12�����Է���12��

    //�ؼ������ҵ�һ����Ķ����ۼӺ�����������
    public static int subArrayMaxSum(int[] arr) {
        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    public static int maxSum(int[] arr) {
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


    //for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));
        System.out.println(subArrayMaxSum(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));
        System.out.println(subArrayMaxSum(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));
        System.out.println(subArrayMaxSum(arr3));

    }


}
