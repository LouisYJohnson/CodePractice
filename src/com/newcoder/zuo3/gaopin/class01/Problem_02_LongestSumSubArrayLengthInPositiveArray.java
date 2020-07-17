package com.newcoder.zuo3.gaopin.class01;

public class Problem_02_LongestSumSubArrayLengthInPositiveArray {
    //��·:�����������Ӵ�:�����Ͽ�����ÿ��λ�ÿ�ʼ��˼·,������i+1λ���ܲ�����iλ�õõ�

    //1������һ�����飬ֵȫ���������뷵���ۼӺ�Ϊ����ֵk��������鳤�ȡ�
    //��������,ÿһ��λ�ÿ�ͷ�ۼӺ�Ϊ����ֵk��������鳤��
    //����l,r��0��ʼ,r���Ҷ�,��==k��ʱ��ֹͣ,���>k,l�ٶ�
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) return 0;

        int r = 0;
        int l = 0;
        int sum = arr[0];
        int maxLen = 0;

        while (r < arr.length) {
            if (sum == k) { //���ǡ�õ���k,Ҫ��l��ǰ�ƶ�ȥѰ����һ��,�����µ�ǰ����󳤶�,�ٰ�sumȥ����ǰl��Ӧ��ֵ(������)
                maxLen = Math.max(maxLen, r - l + 1);
                sum -= arr[l++];
            } else if (sum < k) {    //���<k,��r��ǰ�ƶ�,�����µ�ǰ��(�봰��)
                r++;
                //���r��Խ�߽�,��ֱ��break,���ܸ���sum
                if (r == arr.length) break;
                sum += arr[r];
            } else { //���>k,��l��ǰ�ƶ�,����sum,�Ҳ���ע��l���ܳ���r,���l����r��ͣ,����ľ��Ҳ�����
                sum -= arr[l++];
            }
        }
        return maxLen;
    }

    //for Combinations
    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int getMaxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));
        System.out.println(getMaxLength1(arr, k));
    }
}
