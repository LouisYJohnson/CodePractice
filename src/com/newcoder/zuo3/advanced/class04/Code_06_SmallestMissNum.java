package com.newcoder.zuo3.advanced.class04;

public class Code_06_SmallestMissNum {
    //��Ŀ��
    //������δ���ֵ���С������
    //����Ŀ��
    //����һ��������������arr�� �ҵ�������δ���ֵ���С��������
    //��������
    //arr=[-1,2,3,4]�� ����1��
    //arr=[1,2,3,4]�� ����5��
    //ע���������ҵ���δ���ֵ���С������,����Ҫ�������ų������ϵ�Ԫ�ر������±��1�ľͿ�����
    public static int missNum(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                arr[l] = arr[--r];
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
