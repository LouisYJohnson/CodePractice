package myCodePractice.zuo.basic.class07;

public class Factorial {
    //求n!的结果
    public static long factorial(int n) {
        if (n <= 0) return -1;

        return process(n);
    }

    //递归函数功能:
    //返回n!的结果
    public static long process(int n) {
        //base case
        if (n == 1) return 1;

        return n * process(n - 1);
    }

    //修改动态规划:
    //递归函数中只有一个变量,所以表示结果的dp是一个一维表,范围从1到传入的N
    //为了让下标直接对应传入的N,将数组的0索引设置为0,从数组的1开始塞数
    public static long process1(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 0;
        //看base case确定初始值,n=1时dp应该填的值为1
        dp[1] = 1;
        //看一般坐标下的值该如何确定
        //return后面就是一般坐标下的值的确定方法
        //为当前坐标值乘dp矩阵中当前位置-1位置的值
        for (int i = 2; i < dp.length; i++) {
            dp[i] = i * dp[i - 1];
        }
        //看调用这个递归函数的语句是要的dp矩阵中的哪个值
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(factorial(1));
        System.out.println(process1(1));
        System.out.println(factorial(3));
        System.out.println(process1(3));
        System.out.println(factorial(10));
        System.out.println(process1(10));
        System.out.println(factorial(20));
        System.out.println(process1(20));

    }
}
