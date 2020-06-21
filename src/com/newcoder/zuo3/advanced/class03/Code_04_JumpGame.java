package com.newcoder.zuo3.advanced.class03;

public class Code_04_JumpGame {
    //��Ծ��Ϸ
    //����Ŀ��
    //��������arr�� arr[i]==k������Դ�λ��i������1~k�����롣
    //���磬 arr[2]==3�� �����λ��2��������λ��3�� λ��4��λ��5��
    //�����λ��0������ ��������������������arr����λ���ϡ�
    //��������
    //arr=[3,2,3,1,1,4]��
    //arr[0]==3�� ѡ������λ��2�� arr[2]==3�� ������������λ�á�
    //���Է���2��
    //��Ҫ��
    //���arr����ΪN�� Ҫ��ʵ��ʱ�临�Ӷ�ΪO(N)�� ����ռ临��
    //��ΪO(1)�ķ�����
    //�ⷨ:
    //��������,k,cur,next,
    //kΪ���˼���
    //curΪ��ǰλ�ÿ��Ե�������ұ߽�(��ʾ��ѡ��,�������Χ�ڵĵ㶼������Ϊ��һ���ƶ��ĳ�ʼ��)
    //nextΪ�ڵ�ǰλ�����Ҫ���Ļ�,�����������ұ߽�(��ѡ����ÿһ����Զ���������ĸ�λ��)
    //�����ǰ���Ѿ��ں�ѡ��֮��,��ô��һ�εĺ�ѡ����ǵ�nextλ�õ�,Ҳ������һ�����������к�ѡ������Զ�ľ���

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int k = 0;
        int cur = 0;
        int next = 0;

        for (int i = 0; i < arr.length; i++) {
            //�����ǰλ�õ��˺�ѡ��֮��,���ѡ�㷶Χ����֮ǰ�ĺ�ѡ�����ܹ���������Զλ��
            if (i > cur) {
                cur = next;
                k++;
            }
            //ÿһ�������·�Χ�ں�ѡ���ܹ��ߵ���Զ��λ��
            next = Math.max(next, arr[i] + i);
        }
        return k;
    }

    //for test
    public static int jump1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int jump = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                jump++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 1, 4};
        System.out.println(jump(arr));
        System.out.println(jump1(arr));

    }


}
