package com.newcoder.zuo3.basic.class01;

public class Class_01_BubbleSort {
    //ð�������㷨(�����С��������):
    //˼·:��ͷ��ʼ,ÿ��һ��,ÿ��ð�������ں���,ÿ�ε����һ�����ֲ�������һ������
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
