package com.newcoder.zuo3.advanced.class04;

public class Code_02_ProbabilityXPowerK {
    //����[0,x)�����ϵ������ֵĸ���
    //����Ŀ��
    //���躯��Math.random()�ȸ����������һ����[0,1)��Χ�ϵ�
    //���� ��ô����֪���� ��[0,x)�����ϵ������ֵĸ���Ϊx
    //��0<x��1�� �� ����һ������0������k�� ���ҿ���ʹ��
    //Math.random()������ ��ʵ��һ��������Ȼ������[0,1)��Χ��
    //������ ������[0,x)�����ϵ������ֵĸ���Ϊx^k(0<x��1)��
    //�ⷨ:����x�ļ����ݾ͵��ü���Math.random,ѡ�������Ǹ����ؼ���
    //����:k�ζ���[0,x)֮�ڲ�����Ϊ������������,���Ե���k��ѡ��������,�����������С��x,����Ϊ��һ�γɹ�
    public static double randXPowerK(int k) {
        if (k < 1) return 0;
        double res = -1;
        for (int i = 0; i < k; i++) {
            res = Math.max(Math.random(), res);
        }
        return res;

    }

    //for test
    public static void main(String[] args) {
        double range = 0.5;
        int times = 5000000;
        int count = 0;
        for (int i = 0; i != times; i++) {
            if (randXPowerK(2) < range) {
                count++;
            }
        }
        double p = (double) count / (double) times;
        System.out.println("range [0," + range + "), probability: " + p);
    }

}
