package com.newcoder.zuo3.gaopin.class01;

import java.util.HashMap;

public class unsolve_Problem_02_LongestSubarrayLessSumAwesomeSolution {
    //3������һ�����飬ֵ����Ϊ��������0���뷵���ۼӺ�С�ڵ���k��������鳤�ȡ�

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();//���±����±��Ӧ����С�ۼӺͶ�Ӧ�߽�
        int[] sums = new int[arr.length];   //��ʾ���±�λ�ý�β��Ӧ����С�ۼӺ�
        sums[arr.length - 1] = arr[arr.length - 1];
        hashMap.put(arr.length - 1, arr.length - 1);
        //���ҵ���,����ÿһ��Ԫ��Ϊ��ͷ����С�ۼӺ�����С�ۼӺͶ�Ӧ�ı߽�
        for (int i = arr.length - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                hashMap.put(i, hashMap.get(i + 1));
            } else {
                sums[i] = arr[i];
                hashMap.put(i, i);
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        //��ÿһ���±��Ӧ������
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + sums[end] <= k) {
                sum += sums[end];
                end = hashMap.get(end) + 1;
            }
            sum -= end > i ? arr[i] : 0;
            res = Math.max(res, end - i);
            end = Math.max(end, i + 1);
        }
        return res;
    }

    //for Combinations
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for Combinations
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }

    }
}
