package exams.wangyi;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println(n);
        int index = 0;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            System.out.println(a);
            nums[index++] = a;
        }

        int resNum = 0;
        for (int i = 0; i < nums.length; i++) {
            LinkedList<Integer> helpList = new LinkedList<>();
            LinkedList<LinkedList<Integer>> res = new LinkedList<>();
            getAllSums(nums[i], helpList, res);
            //不拆的话是不是素数
            int itself = isPrime(nums[i]) ? 1 : 0;
            resNum += (itself + countPrime(res));
        }
        System.out.println(resNum);
    }


    //从一个数的所有拆分方法中找到素数最多的那一个
    public static int countPrime(LinkedList<LinkedList<Integer>> temp) {
        int res = 0;
        for (LinkedList<Integer> integers : temp) {
            int help = 0;
            for (Integer integer : integers) {
                if (isPrime(integer)) {
                    help++;
                }
            }
            res = Math.max(res, help);
        }
        return res;

    }

    //回溯法
    //给定当前剩余的数字,返回所有的数字组合
    public static void getAllSums(int n, LinkedList<Integer> helpList, LinkedList<LinkedList<Integer>> res) {
        if (n == 0) {   //没数了,将得到的结果放入res后直接返回
            res.addLast(new LinkedList<>(helpList));
            return;
        } else if (n < 0) { //如果不对直接返回
            return;
        }

        //如果不是,从1开始拆分
        for (int i = 1; i <= n; i++) {
            helpList.addLast(i);
            getAllSums(n - i, helpList, res);
            helpList.removeLast();
        }
    }

    //给一个数,判断是不是素数
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i < (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
