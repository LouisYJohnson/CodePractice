package com.newcoder.zuo3.gaopin.class01;

import java.util.HashMap;

public class Problem_02_LongestSumSubArrayLength {
    //2������һ�����飬ֵ����Ϊ��������0���뷵���ۼӺ�Ϊ����ֵk��������鳤�ȡ�
    //������ÿ��λ�ý�β�ۼӺ�Ϊk��������鳤��
    //��hashMap��,�״γ��ֵ��ۼӺͼ�¼��Ӧ�Ľ�βԪ���±�,
    // ��ô��ľ���key֮���������k���±�(value)֮��,�����е�����,
    // Ҫ��map�г�ʼ����0,-1,�������,��������sum=k���Ӵ�(��Ҫȥ֮ǰ��λ����sum=0������)�Ķ��ᱻ���
    public static int maxLength(int[] arr, int k) {
        //ÿ����������,��Ҫ�ȿ�������֮ǰ���ĺ���hashMap��key���ܲ����ҵ�����Ϊ ��-hashMap �е�keyֵΪk����,�����,���㳤��
        //���û��,�Ѻ����±����hashMap��
        //����������hashMap���Ѿ����ֹ���,�Ͳ�����hashMap�е���ͬkey��Ӧ��value(��ʾ�±�),��ΪҪ����������鳤��
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, -1);
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {//Ҫ��map�г�ʼ����0,-1,�������,��������sum=k���Ӵ�(��Ҫȥ֮ǰ��λ����sum=0������)�Ķ��ᱻ���
            sum += arr[i];
            if (hashMap.containsKey(sum - k)) {
                //��ǰλ��Ϊsum,ϣ��ǰ����ֵsum-��ֵΪk,��sum-?=k,->? = sum-k
                maxLen = Math.max(i - hashMap.get(sum - k), maxLen);//���Ȳ���+1,��Ϊ?���±��ʾ��Ϊ?�Ľ�β�±�,���+1�Ͷ���
            }
            if (!hashMap.containsKey(sum)) {
                hashMap.put(sum, i);
            }
        }
        return maxLen;
    }


    //for test
    public static int maxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1); // important
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));
        System.out.println(maxLength1(arr, 10));

    }
}
