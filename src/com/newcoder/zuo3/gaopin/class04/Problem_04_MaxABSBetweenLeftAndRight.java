package com.newcoder.zuo3.gaopin.class04;

public class Problem_04_MaxABSBetweenLeftAndRight {
    //����һ������ΪN (N>1)����������arr�����Ի��ֳ���������
    //���֣��󲿷�Ϊarr[O..K]���Ҳ���Ϊarr[K+1..N-1]��K����ȡ
    //ֵ�ķ�Χ��[0��N-2]������ô�໮�ַ����У��󲿷��е����ֵ
    //��ȥ�Ҳ������ֵ�ľ���ֵ�У�����Ƕ��٣�
    //���磺[2, 7, 3, 1,1]�����󲿷�Ϊ[2, 7]���Ҳ���Ϊ[3, 1,1]ʱ��
    //�󲿷��е����ֵ��ȥ�Ҳ������ֵ�ľ���ֵΪ4�����󲿷�Ϊ
    //[2, 7, 3]���Ҳ���Ϊ[1,1]ʱ���󲿷��е����ֵ��ȥ�Ҳ�����
    //��ֵ�ľ���ֵΪ6�����кܶ໮�ַ����������շ���6��

    //����������������,�ֱ�洢������ǰ��������,�ұߵ����ֵ
    public static int maxABS1(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;

        int[] helpMaxLeft = new int[arr.length];
        helpMaxLeft[0] = arr[0];
        int helpLeft = arr[0];
        int[] helpMaxRight = new int[arr.length];
        helpMaxRight[arr.length - 1] = arr[arr.length - 1];
        int helpRight = arr[arr.length - 1];

        //����������ֵ������
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > helpLeft) {
                helpLeft = arr[i];
                helpMaxLeft[i] = arr[i];
            } else {
                helpMaxLeft[i] = helpLeft;
            }
        }
        //�����ұ����ֵ������
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > helpRight) {
                helpRight = arr[i];
                helpMaxRight[i] = arr[i];
            } else {
                helpMaxRight[i] = helpRight;
            }
        }

        int maxLeft = 0;
        int maxright = 0;
        int result = 0;
        //�����ұ�������������,��ߵĴ�0��ʼ,��length-2����,�ұߵĴ�1��ʼ��length-1����
        for (int i = 0; i <= arr.length - 2; i++) {
            maxLeft = helpMaxLeft[i];
            maxright = helpMaxRight[i + 1];
            result = Math.max(Math.abs(maxLeft - maxright), result);
        }
        return result;
    }

    //for test
    public static int maxABS2(int[] arr) {
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j != arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }
}
