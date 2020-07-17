package myCodePractice.zuo.advance.class04;

import java.util.Arrays;

public class PrintMaxTopK {
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
    //2． 要求时间复杂度为O(KlogN)

    //一种重要的结构:
    //  堆中堆
    //构建一个大小为一维数组个数的堆
    //将每个一维数组中的最后一个元素(最大的)拿出来,放到堆中
    //放到堆中的元素还应该加入以下信息:
    //  自己是从哪个1维数组来的,是该一维数组的哪一个元素
    //这样在每次弹出堆顶的时候,就知道要从哪个1维数组中拿元素了
    //弹出堆顶后,用弹出元素所属于的1维数组的下一个元素替代堆顶元素,并进行heapify操作
    //如果弹出元素所属于的1维数组已经到头了,那么缩小堆的尺寸
    public static class Node{
        public int arrIndex;    //这个元素属于哪个一维数组
        public int index;       //这个元素在所在的1维数组中是哪个位置
        public int value;       //这个元素的值

        public Node(int arrIndex, int index, int value) {
            this.arrIndex = arrIndex;
            this.index = index;
            this.value = value;
        }
    }

    public static void printMaxTopK(int[][] matrix, int k) {
        if (matrix == null || matrix[0].length == 0 || matrix.length == 0) return;

        int helpCountPrintTime = 0;
        //初始化装着每个数组的最后一个元素的堆
        Node help = null;
        Node[] heap = new Node[matrix.length];
        int heapSize = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            //将每个数组的最后一个元素与该元素对应的信息放到这个Node中去
            help = new Node(i, matrix[i].length - 1, matrix[i][matrix[i].length - 1]);
            heap[i] = help;
            heapInsert(heap, i);
        }
        //每次将heap堆顶元素弹出,并用弹出元素对应的一维数组中的下一个元素替代该位置并进行heapify操作
        //如果对应的一维数组中没有下一个元素了,那就将堆顶元素移除(与堆中最后一个元素交换后heapify堆顶然后堆size-1)
        while (heapSize > 0 && helpCountPrintTime < k) {
            System.out.print(heap[0].value + " ");
            helpCountPrintTime++;
            //如果当前节点属于的数组还有剩余的元素,将剩余的元素拿过来,否则将堆顶元素移除
            if (heap[0].index > 0) {    //有剩余的
                help = new Node(heap[0].arrIndex, heap[0].index - 1, matrix[heap[0].arrIndex][heap[0].index - 1]);
                heap[0] = help;
                heapify(heap, 0, heapSize);
            }else { //没有剩余的,将头部与尾部交换,然后heapSize--相当于删除尾部元素(就是打印完了就删除节点)
                swap(heap, 0, heapSize - 1);
                heapSize--;
                heapify(heap, 0, heapSize);
            }
        }
    }

    //大根堆
    //实现heapify(向下)操作
    //传入表示堆的数组,需要heapify的节点位置和堆的大小
    public static void heapify(Node[] nodes, int index, int heapSize) {
        Node nodeToBeHeapify = nodes[index];
        int leftIndex = 0;
        int rightIndex = 0;
        int helpMax = 0;

        //大根堆
        //在左子或者右子存在的前提下
        //如果小于左子或者右子,和左子右子中最大的换
        while (index < heapSize) {
            leftIndex = index * 2 + 1;
            rightIndex = leftIndex + 1;
            //如果左子右子都在
            if (rightIndex < heapSize) {
                //如果当前节点小于左或者右子,则于左子和右子中选一个更大的交换,如果交换了,就更新index,如果没交换(当前节点比左右孩子都大,就break)
                helpMax = Math.max(nodeToBeHeapify.value, Math.max(nodes[leftIndex].value, nodes[rightIndex].value));
                if (helpMax == nodes[leftIndex].value) {
                    swap(nodes, index, leftIndex);
                    index = leftIndex;
                }else if(helpMax == nodes[rightIndex].value) {
                    swap(nodes, index, rightIndex);
                    index = rightIndex;
                }else {
                    break;
                }
            }else if (leftIndex < heapSize) {   //如果只有左子
                helpMax = Math.max(nodeToBeHeapify.value, nodes[leftIndex].value);
                if (helpMax == nodes[leftIndex].value) {    //如果左子更大,交换并更新坐标,否则break
                    swap(nodes, index, leftIndex);
                    index = leftIndex;
                }else {
                    break;
                }
            }else { //左右子都没有,直接break
                break;
            }
        }
    }

    //大根堆
    //实现heapInsert(向上)操作
    public static void heapInsert(Node[] nodes, int index) {
        Node nodeToBeInsert = nodes[index];
        int helpIndex = (index - 1) / 2;    //表示当前节点的父

        while (helpIndex >= 0) {
            if (nodeToBeInsert.value > nodes[helpIndex].value) {
                swap(nodes, index, helpIndex);
                index = helpIndex;
                helpIndex = (helpIndex - 1) / 2;
            }else {
                break;
            }
        }

    }

    public static void swap(Node[] nodes, int i, int j) {
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] =temp;
    }

    //for Combinations
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



    public static void main(String[] args) {
        int[][] matrix = generateRandomMatrix(5, 10, 1000);
        printMatrix(matrix);
        System.out.println("===========================");
        printMaxTopK(matrix, 5);
    }
}
