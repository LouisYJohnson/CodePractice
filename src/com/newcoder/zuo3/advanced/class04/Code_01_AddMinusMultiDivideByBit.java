package com.newcoder.zuo3.advanced.class04;

public class Code_01_AddMinusMultiDivideByBit {
    //ֻ��λ���㲻����������ʵ�������ļӼ��˳�����
    //����Ŀ��
    //��������32λ����a��b�� ������ �ɸ��� ��0�� ����ʹ����������
    //���� �ֱ�ʵ��a��b�ļӼ��˳����㡣
    //��Ҫ��
    //���������a��bִ�мӼ��˳���ĳЩ��������ͻᵼ�����ݵ�
    //����� ��ô��ʵ�ֵĺ������ض���Щ�������

    //��
    public static int add(int a, int b) {
        int forwardInfo = a & b;    //��λ��Ϣ
        int noForwardInfo = a ^ b;  //�޽�λ��Ϣ
        while (forwardInfo != 0) {
            //��λ��Ϣ����һλ
            forwardInfo = forwardInfo << 1;
            int help = forwardInfo;
            forwardInfo = forwardInfo & noForwardInfo;
            noForwardInfo = help ^ noForwardInfo;
        }
        return noForwardInfo;
    }

    //��,���Ǽ��෴��
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    //����෴��(��λȡ����1)
    public static int negNum(int num) {
        return add(~num, 1);
    }

    //��
    public static int multi(int a, int b) {
        int res = 0;
        int count = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = add(res, a << count);
            }
            b = b >>> 1;//�޷�������,��Ϊ����λ�ǲ��ܲ��������
            count++;
        }
        return res;
    }

    //��:��2��n������,�Ե����ü���Ϊֹ,�Ե�ʱ��,��a,b�����������,��������
    public static int div(int a, int b) {
        int helpA = a < 0 ? negNum(a) : a;
        int helpB = b < 0 ? negNum(b) : b;

        int help = 1 << 30;
        int res = 0;
        while (minus(helpA, multi(helpB, help)) != 0) {
            if (minus(helpA, multi(helpB, help)) < 0) {
                help = help >> 1;
            } else {
                res = add(res, help);
                helpA = minus(helpA, multi(helpB, help));
                help = help >> 1;
            }
        }
        res = add(res, help);
        return res = (a < 0) ^ (b < 0) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new RuntimeException("divisor is 0");
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {//���a����Сֵ,��ô2
            int res = div(add(a, 1), b);
            return add(res, div(minus(a, multi(res, b)), b));
        } else {
            return div(a, b);
        }
    }

    //for test
    public static void main(String[] args) {
        System.out.println(add(10, 29));
        System.out.println(minus(-10, 1));
        System.out.println(multi(-10, -100));
        System.out.println(div(Integer.MIN_VALUE, 2));
        System.out.println(Integer.MIN_VALUE / 2);
    }

}
