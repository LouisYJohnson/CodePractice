package myCodePractice.zuo.basic.class07;

public class MoneyProblem {
    //给你一个数组arr， 和一个整数aim。 如果可以任意选择arr中的
    //数字， 能不能累加得到aim， 返回true或者false
    public static boolean moneyProblem(int[] arr, int aim) {
        if (arr == null || arr.length == 0) return false;

        return process(arr, 0, aim, 0);
    }

    //递归函数功能:
    //i位置之前的数都已经选择完毕,i位置到最后的位置还没有决定
    public static boolean process(int[] arr, int i, int aim, int sum) {
        //base case
        //如果到结尾了,看当前加出的sum和aim是否相同
        //因为递归函数的功能是i位置之前(不包括i位置)的数据都已经选择完毕
        //所以i必须要到arr.length才能表示整个数组都选完了
        if (i == arr.length) {
            return sum == aim;
        }

        //i位置的数选择要或者不要,二者有一个为true就行
        return process(arr, i + 1, aim, sum) || process(arr, i + 1, aim, sum + arr[i]);
    }
}
