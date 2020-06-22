package myCodePractice.zuo.basic.class07;

public class Cow {
    //母牛每年生一只母牛， 新出生的母牛成长三年后也能每年生一只
    //母牛， 假设不会死。 求N年后， 母牛的数量。
    public static int cowProblem(int n) {
        if (n <= 0) return 0;

        return process1(n);
    }

    //递归函数功能:
    //输入年数n,返回第n年牛的个数
    public static int process1(int n) {
        //base case
        //在前三年,牛的个数是每年增加一头的
        if (n <= 3) return n;
        //当年的牛为去年牛的个数的基础上,加上三年前牛的头数
        //牛不会死,所以今年的牛在去年牛的基础上加
        //牛成长三年就可以生小牛了,所以在去年牛的基础上增加的数量为
        //三年前牛的头数
        //比如第6年,6-3=3,第三年出生的牛,过了三年(当年也算),是第5年,在第6年的时候能生小的
        return process1(n - 1) + process1(n - 3);
    }

    public static void main(String[] args) {
        System.out.println(cowProblem(11));
        System.out.println(process2(11));
    }

    //如果每只母牛只能活10年， 求N年后， 母牛的数量。
    //f(N) = f(N - 1) + f(N - 3) - f(N - 10)
    public static int process2(int n) {
        //base case
        //这次的base case,因为会存在n < 0的情况,所以在传入参数小于0的时候返回0即可
        //因为负年份并没有牛
        if (n <= 3 && n >= 0) return n;
        if (n < 0) return 0;

        return process2(n - 1) + process2(n - 3) - process2(n - 10);
    }
}
