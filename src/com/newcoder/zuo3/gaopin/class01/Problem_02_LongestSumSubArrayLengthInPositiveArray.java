package com.newcoder.zuo3.gaopin.class01;

public class Problem_02_LongestSumSubArrayLengthInPositiveArray {
    //套路:看见子数组子串:流程上考虑以每个位置开始的思路,结束看i+1位置能不能由i位置得到

    //1、给定一个数组，值全是正数，请返回累加和为给定值k的最长子数组长度。
    //滑动窗口,每一个位置开头累加和为给定值k的最长子数组长度
    //窗口l,r从0开始,r往右动,到==k的时候停止,如果>k,l再动
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) return 0;

        int r = 0;
        int l = 0;
        int sum = arr[0];
        int maxLen = 0;

        while (r < arr.length) {
            if (sum == k) { //如果恰好等于k,要让l向前移动去寻找下一个,并更新当前的最大长度,再把sum去掉当前l对应的值(出窗口)
                maxLen = Math.max(maxLen, r - l + 1);
                sum -= arr[l++];
            }else if (sum < k) {    //如果<k,让r向前移动,并更新当前和(入窗口)
                r++;
                //如果r超越边界,就直接break,不能更新sum
                if (r == arr.length) break;
                sum += arr[r];
            }else { //如果>k,让l向前移动,更新sum,且不必注意l不能超过r,如果l超过r就停,后面的就找不到了
                sum -= arr[l++];
            }
        }
        return maxLen;
    }

    //for test
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
