package com.newcoder.zuo3.advanced.class04;

import java.util.Arrays;

public class Code_04_PrintMaxTopK {
    //��ӡN��������������Top K
    //����Ŀ��
    //��N�����Ȳ�һ�����飬 ���е����鶼������ģ� ��Ӵ�С��
    //ӡ��N��������������ǰK������
    //���磬 ���뺬��N��Ԫ�صĶ�ά������Դ���N��һά���顣
    //219,405,538,845,971
    //148,558
    //52,99,348,691
    //����������k=5�� ���ӡ��
    //Top 5: 971,845,691,558,538
    //��Ҫ��
    //1�� ������������Ԫ�ظ���С��K�� ��Ӵ�С��ӡ���е�����
    //2�� Ҫ��ʱ�临�Ӷ�ΪO(KlogN)��
    public static class HeapNode {
        private int value;  //�ڵ���ֵ
        private int arrNum; //�ڵ������ڵ�������
        private int index;  //�ڵ��ڱ�Ŷ�Ӧ�����е�����ֵ

        public HeapNode(int value, int arrNum, int index) {
            this.value = value;
            this.arrNum = arrNum;
            this.index = index;
        }
    }

    public static void printTopK(int[][] matrix, int topK) {
        int heapSize = matrix.length;
        HeapNode[] heapNodes = new HeapNode[heapSize];
        //ȡ������һά��������ֵ������heap��
        for (int i = 0; i < heapSize; i++) {
            heapNodes[i] = new HeapNode(matrix[i][matrix[i].length - 1], i, matrix[i].length - 1);
            heapInsert(heapNodes, i);
        }
        //ȡǰtopK����,ÿ�ε����Ѷ�(�Ѷ��ڵ��ڲ�����(�ṹ����)Ȼ��heapify),
        //���û��������С����,ֱ��swap������ǰ������������Ȼ��heapify��heapSize--
        while (heapSize != 0) {
            System.out.print(heapNodes[0].value + " ");
            if (heapNodes[0].index != 0) {
                heapNodes[0].value =
                        matrix[heapNodes[0].arrNum][--heapNodes[0].index];
                heapify(heapNodes, 0, heapSize);
            } else {
                swap(heapNodes, 0, heapSize - 1);
                heapify(heapNodes, 0, --heapSize);
            }
        }
    }

    //�����
    //ʵ��heap�е�heapInsert��heapify����,��Ϊϵͳ�ṩ�����ǵĶѲ���������ڵ��ϸ�ֵ,ֻ�ܼӻ��߼�,��������Ҫ�Լ�ʵ��
    public static void heapInsert(HeapNode[] heapNodes, int index) {
        while (index != 0) {
            if (heapNodes[index].value > heapNodes[(index - 1) / 2].value) {
                swap(heapNodes, index, (index - 1) / 2);
                index = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    //heapify
    public static void heapify(HeapNode[] heapNodes, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        int rightIndex = leftIndex + 1;
        int largestIndex = index;

        while (leftIndex < heapSize) {
            //�������ҳ����ֵ,������ֵ��index�ϵ�value��,�ͽ���,���򲻻�
            if (heapNodes[leftIndex].value > heapNodes[index].value) {
                largestIndex = leftIndex;
            }
            if (rightIndex < heapSize && (heapNodes[rightIndex].value > heapNodes[largestIndex].value)) {
                largestIndex = rightIndex;
            }
            if (largestIndex != index) {
                swap(heapNodes, index, largestIndex);
                index = largestIndex;
            } else {
                break;
            }
            leftIndex = index * 2 + 1;
            rightIndex = leftIndex + 1;
        }
    }

    public static void swap(HeapNode[] heapNodes, int i, int j) {
        HeapNode help = heapNodes[i];
        heapNodes[i] = heapNodes[j];
        heapNodes[j] = help;
    }

    //for test
    public static int[][] generateRandomMatrix(int maxRow, int maxCol,
                                               int maxValue) {
        if (maxRow < 0 || maxCol < 0) {
            return null;
        }
        int[][] matrix = new int[(int) (Math.random() * maxRow) + 1][];
        for (int i = 0; i != matrix.length; i++) {
            matrix[i] = new int[(int) (Math.random() * maxCol) + 1];
            for (int j = 0; j != matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * maxValue);
            }
            Arrays.sort(matrix[i]);
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //implement by zuo
    public static void printTopK1(int[][] matrix, int topK) {
        int heapSize = matrix.length;
        HeapNode[] heap = new HeapNode[heapSize];
        for (int i = 0; i != heapSize; i++) {
            int index = matrix[i].length - 1;
            heap[i] = new HeapNode(matrix[i][index], i, index);
            heapInsert(heap, i);
        }
        System.out.println("TOP " + topK + " : ");
        for (int i = 0; i != topK; i++) {
            if (heapSize == 0) {
                break;
            }
            System.out.print(heap[0].value + " ");
            if (heap[0].index != 0) {
                heap[0].value = matrix[heap[0].arrNum][--heap[0].index];
            } else {
                swap(heap, 0, --heapSize);
            }
            heapify(heap, 0, heapSize);
        }
    }

    public static void heapInsert1(HeapNode[] heap, int index) {
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (heap[parent].value < heap[index].value) {
                swap(heap, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify1(HeapNode[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize) {
            if (heap[left].value > heap[index].value) {
                largest = left;
            }
            if (right < heapSize && heap[right].value > heap[largest].value) {
                largest = right;
            }
            if (largest != index) {
                swap(heap, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }


    public static void main(String[] args) {
        int[][] matrix = generateRandomMatrix(5, 10, 1000);
        printMatrix(matrix);
        System.out.println("===========================");
        printTopK(matrix, 100);
        printTopK1(matrix, 100);
    }


}
