package com.newcoder.zuo3.advanced.class06;

public class Code_03_SubArrayMaxProduct {
    //�����������������۳˻�
    //����Ŀ��
    //����һ��double���͵�����arr�� ���е�Ԫ�ؿ����� �ɸ��� ��0�� ����
    //�������۳˵����˻��� ���磬 arr=[-2.5�� 4�� 0�� 3�� 0.5�� 8�� -1]��
    //������[3�� 0.5�� 8]�۳˿��Ի�����ĳ˻�12�� ���Է���12
    //    Ԥ��������:
//    ���������ÿһ��λ��,ÿһ��λ�������ֵ���Ǳ��������λ���ϵ���Ϊ��β������µõ������˻�
    //ֻ�����������,������������
    //������1:��Ҫǰ�����,ֻҪi
    //������2:Ҫǰ�����,iλ���ϵ�����۳˻���i-1λ���ϵ�����۳˻���iλ�õõ���
    //������3:i-1λ���ϵ���С�۳˻���iλ���ϵĿ��ܵõ�����۳˻�
//    iλ����Ҫi-1λ���ϵ�max,min,���ܵõ����λ���ϵ�max,min
//            ÿ��λ������Ҫ������Ϣ,��һ��λ�ò��ܼ���������
//    �㵽���,ÿ��λ��������max���Ǵ�
    public static double maxProduct(double[] arr) {
        if (arr == null || arr.length == 0) return 0;
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        double maxEnd = 0;
        double minEnd = 0;
        for (int i = 1; i < arr.length; i++) {
            maxEnd = max * arr[i];
            minEnd = min * arr[i];
            //��Ϊ������i��β,����i�ϵ�������Ҫ,��Ҫ��ֻ����ǰ�����
            max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        double[] arr = {-2.5, 4, 0, 3, 0.5, 8, -1};
        System.out.println(maxProduct(arr));

    }
}
