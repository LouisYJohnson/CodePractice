package com.newcoder.zuo3.basic.class02_1;

import java.util.Arrays;

public class Class_02_HeapSort {
    //�����
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int heapSize = arr.length;
        for (int i = 0; i < heapSize; i++) {
            heapInsert(arr, i);
        }
        while (heapSize > 0) {
            swap(arr, 0, heapSize - 1);
            heapSize--;
            heapify(arr, heapSize);
        }
    }

    //����м���һ���ڵ�num,�����ýڵ����Ϊ�����,��һ����������ɲ���,indexΪԭ�Ѵ�С+1
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //heapify����,����Ѷ�ֵ�����仯��,�������µ���Ϊ�����,�����Ǹ��ı��Ԫ��С���������е�һ��,
    //Ӧ�ú��������Ļ�,������ֻҪ��һ��������ľͻ�
    public static void heapify(int[] arr, int heapSize) {
        int index = 0;
        int largest = arr[index];
        while (index < heapSize) {
            int left = index * 2 + 1;
            int right = left + 1;
            //��Ϊ���Ҷ���,��ֻ�������,����û��(index = heapSize-1)
            if (left < heapSize && right < heapSize && arr[left] > arr[right]) {
                //���Ҷ���,����������
                largest = arr[left];
                //��С������,������������¸�����,����������,����ѭ��
                if (arr[index] < largest) {
                    swap(arr, index, left);
                    index = left;
                } else break;
            } else if (left < heapSize && right < heapSize && arr[left] < arr[right]) {
                //���Ҷ���,�������
                largest = arr[right];
                //��С������,������������¸�����,����������,����ѭ��
                if (arr[index] < largest) {
                    swap(arr, index, right);
                    index = right;
                } else break;
            } else if (left < heapSize) {
                //ֻ�������,���ӱȸ���
                if (arr[left] > arr[index]) {
                    largest = arr[left];
                    swap(arr, index, left);
                    index = left;
                } else break;
            } else break;//���Ҷ�û��,����ѭ��

        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}
