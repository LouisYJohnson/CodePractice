package exams.sanliuling;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   //数字为从1到n
        int m = sc.nextInt();   //操作m次
        int[] ops = new int[m]; //包括了所有操作
        for (int i = 0; i < m; i++) {
            ops[i] = sc.nextInt();
        }
        //根据n构造数组
        LinkedList<Integer> helpList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            helpList.addLast(i + 1);
        }

        for (int i = 0; i < ops.length; i++) {
            int curOp = ops[i];
            if (curOp == 1) {
//                swapFirstToEnd(nums);
                int temp = helpList.removeFirst();
                helpList.addLast(temp);
            }
            if (curOp == 2) {
                swapBetween(helpList);
            }
        }

        for (int i = 0; i < helpList.size(); i++) {
            if (i == helpList.size() - 1) {
                System.out.print(helpList.get(i));
            }else {
                System.out.print(helpList.get(i));
                System.out.print(" ");
            }
        }

//        for (int i = 0; i < nums.length; i++) {
//            if (i == nums.length - 1) {
//                System.out.print(nums[i]);
//            }else {
//                System.out.print(nums[i]);
//                System.out.print(" ");
//            }
//        }

    }

    public static void swapFirstToEnd(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            swap(nums, i, i + 1);
        }
    }

    public static void swapBetween(int[] nums) {
        for (int i = 0; i < nums.length; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void swapBetween(LinkedList<Integer> helpList) {
        for (int i = 0; i < helpList.size(); i += 2) {
            int temp1 = helpList.get(i);
            int temp2 = helpList.get(i + 1);
            helpList.set(i, temp2);
            helpList.set(i + 1, temp1);
        }
    }
}
