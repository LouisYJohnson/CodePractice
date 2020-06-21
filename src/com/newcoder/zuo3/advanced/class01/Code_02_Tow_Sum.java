package com.newcoder.zuo3.advanced.class01;

import java.util.HashMap;

public class Code_02_Tow_Sum {
//    给定一个数组arr， 和一个整数aim， 请返回哪两个位置的数可
//    以加出aim来。
//    例如
//            arr = {2, 7, 11, 15}， target = 9
//    返回{0,1}， 因为arr[0] + arr[1] = 2
    //方法2是进阶版的,可以用在无序数组上(有序数组不能使用双指针解决),如果是有序数组,就不需要排序了(方法1有序无序都可以)

    //方法1:使用HashMap来做:key为数字,value为数字对应的序号
    //从头遍历数组,如果遍历到得数字和在set中有target-当前数字相等的,返回位置
    public static int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            //如果遍历到得数字和在set中有target-当前数字相等的,返回位置
            if (hashMap.containsKey(target - nums[i])) return new int[]{i, hashMap.get(target - nums[i])};
        }
        return null;
    }


    //方法2 将数组排序后记录数组初始位置,使用双指针找到对应数组元素(左右指针一开始指向数组两端,如果碰到左右之和小于target,左指针右移,如果碰到左右之和大于target,右指针左移)
    // (在堆排序中整合一个数组,这个数组负责记录元素初始位置),直接将这个带着记录元素初始位置的数组扔到swap中让他参与每次的swap运算就可以了
    public static int[] twoSum2(int[] nums, int target) {
        //构建一个数组专门存储初始数字的位置
        int[] indexArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexArr[i] = i;
        }
        //这个方法之后返回的数组indexArr中就对应着排序后的数字在初始数组nums中的位置,而不是返回的已经排好序的这个nums数组
        heapSort(nums, indexArr);
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                return new int[]{indexArr[l], indexArr[r]};
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }

    //实现堆排序(大根堆):
    public static void heapSort(int[] arr, int[] indexArr) {
        if (arr == null || arr.length < 2) return;
        //拿到数组后,将数组中元素依次加入表示堆的数组中(heapInsert),组成大根堆
        for (int i = 1; i < arr.length; i++) {
            heapInsert(arr, i, indexArr);
        }
        int heapSize = arr.length;
        //每次从堆顶取出元素(即为当前最大值,方法为和数组尾部元素交换,然后将堆顶元素heapify,heapSize--)
        //这样操作后的数组就是从小到大排序的
        while (heapSize != 0) {
            swap(arr, 0, heapSize - 1, indexArr);
            heapify(arr, 0, heapSize - 1, indexArr);
            heapSize--;
        }
//        int size = arr.length;
//        swap(arr, 0, --size);
//        while (size > 0) {
//            heapify(arr, 0, size);
//            swap(arr, 0, --size);
//        }

    }

    //heapInsert,在表示堆的数组部分的尾部加入数字,并一路找父交换
    public static void heapInsert(int[] arr, int index, int[] indexArr) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2, indexArr);
            index = (index - 1) / 2;
        }
    }

    //heapify,在表示堆的数组index位置的数字发生变化(一般是头部),需要调整这个堆,数组从头开始一路和左右子比较(但是注意不能越界)
    //因为数组中只有heapSize内部的东西才是堆中的,外面的不是
    public static void heapify(int[] arr, int index, int heapSize, int[] indexArr) {
        int left = 2 * index + 1;
        int right = left + 1;
        while (left < heapSize) {//不需要右边也小于heapSize,因为右边有可能并不参加交换
            //先从左子与右子中找出最大的,需要坐标才能swap,所以这里largest代表的必须是坐标值
            int largestIndex = right < heapSize && arr[left] < arr[right] ? right : left;
            //如果当前的数字小于左右子中最大的,才交换,交换后记得更新当前index,left,right
            if (arr[index] < arr[largestIndex]) {
                swap(arr, index, largestIndex, indexArr);
                index = largestIndex;
                left = 2 * index + 1;
                right = left + 1;
            } else {//否则,不用找了,直接跳出循环即可,不跳出是死循环
                break;
            }

        }
    }

    //交换数组中i和j位置上的值
    public static void swap(int[] arr, int i, int j, int[] indexArr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        temp = indexArr[i];
        indexArr[i] = indexArr[j];
        indexArr[j] = temp;
    }

    public static void main(String[] args) {
        int[] test = {1};
        int target = 5;
        int[] result = null;
        result = twoSum1(test, target);
        int[] result1 = twoSum2(test, target);
        printArray(result);
        printArray(result1);

    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("arr has no value!");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


//    // for test
//    public static void comparator(int[] arr) {
//        Arrays.sort(arr);
//    }
//
//    // for test
//    public static int[] generateRandomArray(int maxSize, int maxValue) {
//        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
//        }
//        return arr;
//    }
//
//    // for test
//    public static int[] copyArray(int[] arr) {
//        if (arr == null) {
//            return null;
//        }
//        int[] res = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            res[i] = arr[i];
//        }
//        return res;
//    }
//
//    // for test
//    public static boolean isEqual(int[] arr1, int[] arr2) {
//        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
//            return false;
//        }
//        if (arr1 == null && arr2 == null) {
//            return true;
//        }
//        if (arr1.length != arr2.length) {
//            return false;
//        }
//        for (int i = 0; i < arr1.length; i++) {
//            if (arr1[i] != arr2[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // for test
//    public static void printArray(int[] arr) {
//        if (arr == null) {
//            return;
//        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//    }
//
//    // for test
//    public static void main(String[] args) {
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            heapSort(arr1);
//            comparator(arr2);
//            if (!isEqual(arr1, arr2)) {
//                succeed = false;
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//
//        int[] arr = generateRandomArray(maxSize, maxValue);
//        printArray(arr);
//        heapSort(arr);
//        printArray(arr);
//    }
}
