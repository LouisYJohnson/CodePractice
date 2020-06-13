package com.newcoder.zuo3.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Class_04_MadianQuick {
    //随时找到数据流的中位数
    //【题目】
    //有一个源源不断地吐出整数的数据流， 假设你有足够的空间来
    //保存吐出的数。 请设计一个名叫MedianHolder的结构，
    //MedianHolder可以随时取得之前吐出所有数的中位数。
    //【要求】
    //1． 如果MedianHolder已经保存了吐出的N个数， 那么任意时刻
    //将一个新数加入到MedianHolder的过程， 其时间复杂度是
    //O(logN)。
    //2． 取得已经吐出的N个数整体的中位数的过程， 时间复杂度为
    //O(1)。

    //    随时找到数据流的中位数
    public static class MedianHolder {
        private PriorityQueue<Integer> bigHeap = new PriorityQueue<Integer>(100, new bigHeapComparator());
        private PriorityQueue<Integer> smallHeap = new PriorityQueue<Integer>(100, new smallHeapComparator());


        public void modifyTwoHeapsSize() {
            if (bigHeap.size() - smallHeap.size() == 2) {
                smallHeap.add(bigHeap.poll());
            } else if (smallHeap.size() - bigHeap.size() == 2) {
                bigHeap.add(smallHeap.poll());
            }
        }

        //    public void addNumber(int num) {
//        if (this.bigHeap.isEmpty()) {
//            this.bigHeap.add(num);
//            return;
//        }
//        if (this.bigHeap.peek() >= num) {
//            this.bigHeap.add(num);
//        } else {
//            if (this.smallHeap.isEmpty()) {
//                this.smallHeap.add(num);
//                return;
//            }
//            if (this.smallHeap.peek() > num) {
//                this.bigHeap.add(num);
//            } else {
//                this.smallHeap.add(num);
//            }
//        }
//        modifyTwoHeapsSize();
//    }
        public void addNumber(Integer num) {
            //前两个数添加进来的时候会进入前两个if语句中
            //第三个数进来的时候有可能进入第三个if语句中
            //后面的数进来前三个if都不可能成立
            //因为前两个是在一开始堆中没有数不经过判断直接加数的
            //第三个是在堆中加完两个数看大堆是否存着在收到的数中较小的数以及小堆是否存着大数
            //后面的if是用来正常添加数的
            if (bigHeap.isEmpty()) {//保证bigHeap不空
                bigHeap.add(num);
            } else if (smallHeap.isEmpty()) {//保证smallHeap不空
                smallHeap.add(num);
            } else if (smallHeap.peek() < bigHeap.peek()) {
                int tempSmall = smallHeap.poll();
                int tempBig = bigHeap.poll();
                smallHeap.add(tempBig);
                bigHeap.add(tempSmall);
                if (num < bigHeap.peek()) {
                    bigHeap.add(num);
                } else if (num > smallHeap.peek()) {
                    smallHeap.add(num);
                } else smallHeap.add(num);
            } else if (num < bigHeap.peek()) {
                bigHeap.add(num);
            } else if (num > smallHeap.peek()) {
                smallHeap.add(num);
            } else smallHeap.add(num);
            modifyTwoHeapsSize();
        }

        public Integer getMedian() {
            if (bigHeap.size() + smallHeap.size() < 1) return null;
            if ((bigHeap.size() + smallHeap.size()) % 2 == 1) {//如果有奇数个元素
                if (bigHeap.size() - smallHeap.size() > 0) return bigHeap.peek();
                if (smallHeap.size() - bigHeap.size() > 0) return smallHeap.peek();
            } else return (bigHeap.peek() + smallHeap.peek()) / 2;
            return null;
        }
    }

    //大根堆比较器
    public static class bigHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            //表示大的放上面(堆中)或前面(数组中)
            return o2 - o1;
        }
    }

    //小根堆比较器
    public static class smallHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            //表示小的放上面(堆中)或前面(数组中)
            return o1 - o2;
        }
    }

    // for test
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
//            int[] arr = {693,41 ,213, 863, 745, 54, 235, 35, 120, 749};
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);
            }
            int med = medianHold.getMedian();
            int get = getMedianOfArray(arr);
//            if (medianHold.getMedian() != getMedianOfArray(arr)) {
            if (med != get) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

    }

}
