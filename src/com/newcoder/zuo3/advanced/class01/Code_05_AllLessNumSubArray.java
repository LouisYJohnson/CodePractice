package com.newcoder.zuo3.advanced.class01;

import java.util.LinkedList;

public class Code_05_AllLessNumSubArray {
    //��������arr������num�� �������ж��ٸ��������������������
    //max(arr[i..j]) - min(arr[i..j]) <= num
    //max(arr[i..j])��ʾ������arr[i..j]�е����ֵ�� min(arr[i..j])��ʾ������arr[i.
    //�е���Сֵ��
    //��Ҫ��
    //������鳤��ΪN�� ��ʵ��ʱ�临�Ӷ�ΪO(N)�Ľⷨ
    //����:���һ����������,��ô��������������Ҳ�Ǵ���(���������������,���ֵֻ�п��ܱ�С���߲���,��Сֵֻ�п��ܱ����߲���,������Ȼ���)
    //���һ�����鲻���,��ô������鲻��������������������,��������.��Ϊ���������ֵֻ�п��ܸ�����߲���,��Сֵֻ�п��ܸ�С���߲���
//    ����:(����˼·Ϊ�������ҵ���������ÿһ��Ԫ��Ϊ��ʼ�Ĳ��Ҵ���������)
//    �������ṹ,�õ������е����ֵ����Сֵ
//    l��0��ʼ,rÿ��һ��,��һ���Ƿ���,������,����,ֱ����������Խ��Ϊֹ,�õ�����0-0,0-1,...,0-r��������,0��r�����Ȳ�����,���ǵõ�0��ʼ�Ĵ�������������
//    Ȼ����L��1��ʼ,Ȼ��r�Ƿ���������(0��r���,��ô1��rһ��Ҳ���),r����֮��,1��r�ϵ���һ���Ȳ����оͺ�(���������),�͵õ���1��ͷ�Ĵ�������������
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //�������ṹ�ֱ�洢�������������Сֵ(���ִ��ڽṹ�ǽ���ʱ�临�ӶȵĹؼ�)
        //���ڽṹ�洢����Ԫ���±꣬�ò��Ŵ�Ԫ����Ϊͨ���±�����ҵ�Ԫ��
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        //����ָ��,�ֱ�ָ��ǰԪ��,�뵱ǰԪ�ص���������
        int i = 0;
        int j = 0;
        //�洢���
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                //�ҵ���i��j�����ֵ
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                //�Ҵ�i��j����Сֵ
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                j++;
            }
            //�������ƣ���մ����������/��Сֵ
            if (i == qmax.peekFirst()) {
                qmax.pollFirst();
            }
            if (i == qmin.peekFirst()) {
                qmin.pollFirst();
            }
            res += j - i;
            i++;
        }
        return res;
    }

    //implement by zuo
    public static int getNum1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            res += j - i;
            i++;
        }
        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum1(arr, num));
        System.out.println(getNum(arr, num));

    }
}
