package com.newcoder.zuo3.gaopin.class03;

public class Problem_02_Islands {
    //����һ����ά���飬����λ�õ�ֵ����0����1���涨ÿ��λ��
    //���Ժ�����������λ���ϵ�ֵ������
    //��һ���������ĸ���������£�
    //����һƬ��1�������Χ����0����ô��һƬ1������һ������
    //������ͼ���ж��ٸ�����
    //���磺
    //0 0 0 0 0 0 0 0 0
    //0 1 1 0 0 1 1 1 0
    //0 1 1 1 0 0 0 1 0
    //0 1 1 0 0 0 0 0 0
    //0 0 0 0 0 1 1 0 0
    //0 0 0 0 1 1 1 0 0
    //000000000
    //����ͼ����������
    //0 0 0 0 0 0 0 0 0
    //0 1 1 0 1 1 1 1 0
    //0 1 1 1 1 0 0 1 0
    //0 1 1 0 0 0 0 1 0
    //0 0 0 0 0 1 1 1 0
    //0 0 0 0 1 1 1 0 0
    //0 0 0 0 0 0 0 0 0
    //����ͼ����һ������

    //�õݹ�����,�������еĵ�,�ݹ麯������Ϊ:����1������Ϊ2�������������ĸ���������޸�
    public static int countIslands(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;

        int rows = m.length;
        int cols = m[0].length;
        int res = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    infect(m, i, j, rows, cols);
                    res++;
                }
            }
        }
        return res;
    }

    public static void infect(int[][] m, int i, int j, int rows, int cols) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || m[i][j] != 1) return;
        m[i][j] = 2;
        infect(m, i + 1, j, rows, cols);
        infect(m, i, j + 1, rows, cols);
        infect(m, i - 1, j, rows, cols);
        infect(m, i, j - 1, rows, cols);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m1));

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m2));

    }

}
