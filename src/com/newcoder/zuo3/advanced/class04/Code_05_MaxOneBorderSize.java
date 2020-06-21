package com.newcoder.zuo3.advanced.class04;

public class Code_05_MaxOneBorderSize {
    //�߽綼��1����������δ�С
    //����Ŀ��
    //����һ��NN�ľ���matrix�� ����������У� ֻ��0��1����ֵ�� ���ر߿�ȫ��1���������
    //�εı߳����ȡ�
    //���磺
    //0 1 1 1 1
    //0 1 0 0 1
    //0 1 0 0 1
    //0 1 1 1 1
    //0 1 0 1 1
    //���У� �߿�ȫ��1����������εĴ�СΪ4*4�� ���Է���4
    //���취:�ҵ�����������,������е���������ʼ��,����ʼ�㿪ʼ�����п��ܱ߳���������
    //
    //�÷���:Ԥ��������,������������,��С��ԭ������ͬ,�ֱ�Ϊdown�����right����,
    // �����е�ֵ��ʾ�������λ�������·������ұ��ж��ٸ�������1,���������0,��ô����0
    //�÷���:
    //��������������,��С��ԭ������ͬ,�ֱ�Ϊdown�����right����,
    //�����е�ֵ��ʾ�������λ�������·������ұ��ж��ٸ�������1,���������0,��ô����0
    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        //down:ÿ�д�������,��m��ͬһ��λ�õ���,�����ǰλ�õ�����0,�Ǿ���0,�����1,���������down���������Ԫ��
        for (int j = 0; j < m[0].length; j++) { //��
            for (int i = m.length - 1; i >= 0; i--) { //��
                //����Խ��(��������һ�л���)
                if (i + 1 == m.length) {
                    down[i][j] = m[i][j] == 1 ? 1 : 0;
                } else {
                    down[i][j] = m[i][j] == 1 ? 1 + down[i + 1][j] : 0;
                }
            }
        }
        //right:ÿ�д��ҵ���,��m��ͬһ��λ�õ���,�����ǰλ�õ�����0,�Ǿ���0,�����1,���������right�����Ҳ����
        for (int i = 0; i < m.length; i++) {    //��
            for (int j = m[0].length - 1; j >= 0; j--) {    //��
                //����Խ��(�����ұ߻���)
                if (j + 1 == m[0].length) {
                    right[i][j] = m[i][j] == 1 ? 1 : 0;
                } else {
                    right[i][j] = m[i][j] == 1 ? 1 + right[i][j + 1] : 0;
                }
            }
        }
    }

    //����ǳ�����,�����ܴ��ڵ����������ֻ���ǳ�������̵ı߳�
    public static int getMaxSize(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
//        printMatrix(right);
//        System.out.println();
//        printMatrix(down);
        //�����߳��������ο�ʼ��,�ҵ���return true,�����һֱ������
        for (int size = Math.min(m.length, m[0].length); size > 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size;
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        //����right��down����,Ѱ���Ƿ�������size��Ԫ��
        for (int i = 0; i < right.length; i++) {
            for (int j = 0; j < right[0].length; j++) {
                if (Math.min(right[i][j], down[i][j]) >= size) {
                    int rightEleDown = down[i][j + right[i][j] - 1];
                    int downEleRight = right[i + down[i][j] - 1][j];
                    if (Math.min(rightEleDown, downEleRight) >= size) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //for test
    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println();
        System.out.println(getMaxSize(matrix));
    }

}
