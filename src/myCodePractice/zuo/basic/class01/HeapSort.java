package myCodePractice.zuo.basic.class01;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort {
    //С����
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 1) return;

        int heapSize = arr.length;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        while (heapSize > 0) {
            swap(arr, 0, heapSize - 1);
            heapSize--;
            heapify(arr, heapSize);
        }
    }

    //��arr��iλ�õ�Ԫ�ز��뵽С������
    //����
    public static void heapInsert(int[] arr, int i) {
        while (arr[i] < arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    //heapify����
    //����
    public static void heapify(int[] arr, int heapSize) {
        int index = 0;

        while (index < heapSize) {
            int leftSon = index * 2 + 1;
            int rightSon = leftSon + 1;
            int small = arr[index];

            //��Ϊ����ȫ������,���Է�Ϊ�����Ӷ���,ֻ������
            if (rightSon < heapSize && arr[leftSon] < arr[rightSon]) { //�����Ӷ���,���������ڶ����и�С
                if (arr[leftSon] < small) { //����Ϊ��������С,��������������Ϊͷ
                    swap(arr, index, leftSon);
                    index = leftSon;
                } else {    //ͷ��С,����
                    break;
                }
            } else if (rightSon < heapSize && arr[rightSon] < arr[leftSon]) {   //�����Ӷ���,�����ڶ����и�С
                if (arr[rightSon] < small) {
                    swap(arr, index, rightSon);
                    index = rightSon;
                } else {
                    break;
                }
            } else if (leftSon < heapSize) {   //ֻ������
                if (arr[leftSon] < small) {
                    swap(arr, index, leftSon);
                    index = leftSon;
                } else {
                    break;
                }
            } else {    //�����Ӷ�û��,����ѭ��
                break;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static void comparator(Integer[] arr) {
        Arrays.sort(arr, new Comparator<Integer>(
        ) {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
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
    public static boolean isEqual(Integer[] arr1, Integer[] arr2) {
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

            Integer[] arr3 = new Integer[arr2.length];
            for (int j = 0; j < arr3.length; j++) {
                arr3[j] = arr2[j];
            }
            comparator(arr3);
            Integer[] arr4 = new Integer[arr2.length];
            for (int j = 0; j < arr3.length; j++) {
                arr4[j] = arr1[j];
            }


            if (!isEqual(arr3, arr4)) {
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
