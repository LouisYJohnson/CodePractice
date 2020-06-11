package com.newcoder.zuo3.basic.class07;

public class Money_Problem {
    //给你一个数组arr， 和一个整数aim。 如果可以任意选择arr中的
    //数字， 能不能累加得到aim， 返回true或者false
    public static boolean money1(int[] arr, int aim) {
        //定义一个可以转化为子流程的函数
        //从0开始的数列一直到最后
        //i:在在此位置之前(不包含此位置)的所有数中进行选择
        //sum:在i之前的所有数任选后得到的和
        //参数:arr,i,sum,aim
        return process1(arr,0,0,aim);

    }
    //递归版本
    public static boolean process1(int[] arr, int i, int sum, int aim) {
        //base case:
        if (i == arr.length) return sum == aim;
        //i会一直走到arr.length为止,超过数组一个,如果不这样的话,让i对应数组下标,从0处的sum就不好定,
        // 因为有可能不要在0处的值,sum就必为3了,显然与题意不符

        //子过程是要这个i上的数或者不要i上的数
        return process1(arr,i+1,sum+arr[i],aim) || process1(arr,i+1,sum,aim);
    }
    //非递归版本
    //i,sum定了,结果就定了(可以画个图,穷举所有结果,发现还是i,sum决定了结果)
    public static boolean money2(int[] arr, int aim) {
        //i的范围为0-N
        //sum的范围为0到arr中所有数相加(因为有可能一个数都不选,也有可能都选,就算不选加上从0开始也无所谓
        // 因为在计算的时候根本不会在解空间中不存在情况的点上填东西,也不可能要解空间中不存在数的地方要结果)
        int sumAll = 0;
        for (int i : arr) {
            sumAll += i;
        }
        //构建解空间:
        boolean[][] solveSpace = new boolean[arr.length+1][sumAll+1];
        //解空间中要的结果为(0,0),看base case中不依赖其他位置的点为sum==aim,与i==arr.length的时候
        //此处注意行号与列号代表的含义,行号代表i的值,如果是0,就是i=0,列号代表sum的值,如果为0就是sum=0
        //该行的其他所有列,都是false,因为i前面选择了那些数得到的sum值,都是错误的,并且也没有其他数让他们选了
        for (boolean booleans : solveSpace[arr.length]) {
            booleans = false;
        }
        solveSpace[arr.length][aim] = true;
        //去递归中找公式,看普遍的(i,sum)该怎么对应其他点
        //由于解空间中最后一行已经填好了,而且每个点只依赖下面一行的两个点,所以从倒数第二行开始从左至右算点
        for (int i = arr.length-1; i >= 0; i--) {
            for (int j = 0; j <= sumAll; j++) {
                if (j+arr[i] > sumAll) continue;//有可能越界,可以创建一个更大的数组,也可以不作处理直接跳过当前循环,因为结果不会与这个没有处理的点有关
                solveSpace[i][j] = solveSpace[i+1][j+arr[i]] || solveSpace[i+1][j];
            }
        }
        return solveSpace[0][0];
    }
    public static void main(String[] args) {
        int[] arr = { 1, 4, 8 };
        int aim = 12;
        System.out.println(money1(arr, aim));
        System.out.println(money2(arr, aim));
    }
}
