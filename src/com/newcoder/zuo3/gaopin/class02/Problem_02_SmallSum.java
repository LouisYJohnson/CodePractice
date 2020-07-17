package com.newcoder.zuo3.gaopin.class02;

public class Problem_02_SmallSum {
    //����С�͵Ķ������£�
    //���磬����3=[1,3,5,2,4,6]����3[0]�����С�ڻ����3[0]��
    //�� �� �� Ϊ 0 , �� s[ 1] �����С�ڻ���� s[ 1] �����ĺ�Ϊ1,�� s[ 2]
    //�����С�ڻ����s [2]�����ĺ�Ϊ1 +3=4,��s [3]�����С�ڻ�
    //����s [3]�����ĺ�Ϊ1����s [4]�����С�ڻ����s [4]�����ĺ�
    //Ϊ1+3+2=6����s [5]�����С�ڻ����s [5]�����ĺ�Ϊ
    //1+3+5+2+4=15������s��С��Ϊ0+1+4+1+6+15=27��
    //����һ������s��ʵ�ֺ�������s��С��

    //mergeSort��д����
    //���ȸ�ϰһ��mergeSort
    //�ݹ麯������:������������ұ߽�,����������
//    public static void mergeSort(int[] arr, int l, int r) {
//        //base case
//        if (l == r) return;
//
//        int mid = l + (r - l) / 2;
//        //�������źú�,�ϲ�����
//        mergeSort(arr, l, mid);
//        mergeSort(arr, mid + 1, r);
//        merge(arr, l, mid, r);
//    }
//
//    //����һ������,���м�ֳ�����,��ߴ�С����,�ұ�Ҳ�Ǵ�С����,�����������ֺϲ���һ������Ĵ�С����
//    public static void merge(int[] arr, int l, int mid, int r) {
//        int pL = l;
//        int pR = mid + 1;
//        int[] help = new int[r - l + 1];
//        int indexHelp = 0;
//
//        while (pL <= mid && pR <= r) {
//            if (arr[pL] < arr[pR]) {
//                help[indexHelp++] = arr[pL++];
//            } else {
//                help[indexHelp++] = arr[pR++];
//            }
//        }
//        //����������,���ܴ������û��������ұ�û����
//        while (pL <= mid) {
//            help[indexHelp++] = arr[pL++];
//        }
//        while (pR <= r) {
//            help[indexHelp++] = arr[pR++];
//        }
//        //��help�е����ݿ�����arr��
//        for (int i = 0; i < help.length; i++) {
////            ���ﲻ���Ǵ�i��ʼ,��Ϊarr����߽���l������0!
//            arr[l++] = help[i];
//        }
//    }

    //���С��������Ǹ�дmergeSort,��merge��ʱ�����������С���Ҳ�,С�ͼ�Ϊ�����������Ҳ�ָ������Ҳ��β���
    public static int getSmallSum1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return smallSum(arr, 0, arr.length - 1);
    }


    public static int smallSum(int[] arr, int l, int r) {
        //base case
        if (l == r) return 0;

        int mid = l + (r - l) / 2;
        return smallSum(arr, l, mid) + smallSum(arr, mid + 1, r) + mergeSum(arr, l, mid, r);
    }

    public static int mergeSum(int[] arr, int l, int mid, int r) {
        int pL = l;
        int pR = mid + 1;
        int result = 0;
        int[] help = new int[r - l + 1];
        int indexHelp = 0;

        while (pL <= mid && pR <= r) {
            if (arr[pL] <= arr[pR]) {
                //pR�ұ��м�������arr[pR]��,���м���arr[pL]
                result += arr[pL] * (r - pR + 1);
                help[indexHelp++] = arr[pL++];
            } else {
                help[indexHelp++] = arr[pR++];
            }
        }
        while (pL <= mid) {
            help[indexHelp++] = arr[pL++];
        }
        while (pR <= r) {
            help[indexHelp++] = arr[pR++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }
        return result;

    }


    //for Combinations
    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return func(arr, 0, arr.length - 1);
    }

    public static int func(int[] s, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
    }

    public static int merge(int[] s, int left, int mid, int right) {
        int[] h = new int[right - left + 1];
        int hi = 0;
        int i = left;
        int j = mid + 1;
        int smallSum = 0;
        while (i <= mid && j <= right) {
            if (s[i] <= s[j]) {
                smallSum += s[i] * (right - j + 1);
                h[hi++] = s[i++];
            } else {
                h[hi++] = s[j++];
            }
        }
//        for (; (j < right + 1) || (i < mid + 1); j++, i++) {
//            h[hi++] = i > mid ? s[j] : s[i];
//        }
//        for (int k = 0; k != h.length; k++) {
//            s[left++] = h[k];
//        }
        while (i <= mid) {
            h[hi++] = s[i++];
        }
        while (j <= right) {
            h[hi++] = s[j++];
        }
        for (int k = 0; k < h.length; k++) {
            s[left++] = h[k];
        }
        return smallSum;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        //���Ե�ʱ��Ҫ������������,��ΪmergeSort��С�������ǻ��������������,�ղ�!!!
        int[] arr = {3, 1, 2, 4, 6, 2, 7, 8, 1};
        int[] arr1 = {3, 1, 2, 4, 6, 2, 7, 8, 1};
        System.out.println(getSmallSum(arr));
        System.out.println(getSmallSum1(arr1));

    }

}
