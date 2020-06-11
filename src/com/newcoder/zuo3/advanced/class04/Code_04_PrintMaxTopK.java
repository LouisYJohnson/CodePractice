package com.newcoder.zuo3.advanced.class04;

import java.util.Arrays;

public class Code_04_PrintMaxTopK {
    //打印N个数组整体最大的Top K
    //【题目】
    //有N个长度不一的数组， 所有的数组都是有序的， 请从大到小打
    //印这N个数组整体最大的前K个数。
    //例如， 输入含有N行元素的二维数组可以代表N个一维数组。
    //219,405,538,845,971
    //148,558
    //52,99,348,691
    //再输入整数k=5， 则打印：
    //Top 5: 971,845,691,558,538
    //【要求】
    //1． 如果所有数组的元素个数小于K， 则从大到小打印所有的数。
    //2． 要求时间复杂度为O(KlogN)。
    public static class HeapNode {
        private int value;  //节点数值
        private int arrNum; //节点所属于的数组标号
        private int index;  //节点在标号对应数组中的索引值

        public HeapNode(int value,int arrNum,int index) {
            this.value = value;
            this.arrNum = arrNum;
            this.index = index;
        }
    }

    public static void printTopK(int[][] matrix, int topK) {
        int heapSize = matrix.length;
        HeapNode[] heapNodes = new HeapNode[heapSize];
        //取到各个一维数组的最大值并放入heap中
        for (int i = 0; i < heapSize; i++) {
            heapNodes[i] = new HeapNode(matrix[i][matrix[i].length - 1],i,matrix[i].length - 1);
            heapInsert(heapNodes,i);
        }
        //取前topK的数,每次弹出堆顶(堆顶节点内部更新(结构复用)然后heapify),
        //如果没有索引更小的数,直接swap数组最前面与最后面的数然后heapify并heapSize--
        while (heapSize != 0) {
            System.out.print(heapNodes[0].value + " ");
            if (heapNodes[0].index != 0) {
                heapNodes[0].value =
                        matrix[heapNodes[0].arrNum][--heapNodes[0].index];
                heapify(heapNodes,0,heapSize);
            }else {
                swap(heapNodes,0,heapSize - 1);
                heapify(heapNodes,0,--heapSize);
            }
        }
    }

    //大根堆
    //实现heap中的heapInsert与heapify功能,因为系统提供给我们的堆不能在任意节点上改值,只能加或者减,所以我们要自己实现
    public static void heapInsert(HeapNode[] heapNodes,int index) {
        while (index != 0) {
            if (heapNodes[index].value > heapNodes[(index - 1) / 2].value) {
                swap(heapNodes,index,(index - 1) / 2);
                index = (index - 1) / 2;
            }else {
                break;
            }
        }
    }
    //heapify
    public static void heapify(HeapNode[] heapNodes,int index,int heapSize) {
        int leftIndex = index * 2 + 1;
        int rightIndex = leftIndex + 1;
        int largestIndex = index;

        while (leftIndex < heapSize) {
            //从左右找出最大值,如果最大值比index上的value大,就交换,否则不换
            if (heapNodes[leftIndex].value > heapNodes[index].value) {
                largestIndex = leftIndex;
            }
            if (rightIndex < heapSize && (heapNodes[rightIndex].value > heapNodes[largestIndex].value)) {
                largestIndex = rightIndex;
            }
            if (largestIndex != index) {
                swap(heapNodes,index,largestIndex);
                index = largestIndex;
            }else {
                break;
            }
            leftIndex = index * 2 + 1;
            rightIndex = leftIndex + 1;
        }
    }

    public static void swap(HeapNode[] heapNodes,int i,int j) {
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
