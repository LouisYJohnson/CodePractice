package myCodePractice.zuo.basic.class08;

public class SubArrayMaxSum {
    //给定一个数组arr， 返回所有子数组的累加和中， 最大的累加和
    //实际上是在找长度最长并且和最大的子数组
    //两个变量搞定,cur与max
    //两个变量的变化规则:
    //max初始值为Integer.MIN_VALUE,cur初始值为0
    //遍历数组,每来一个数,cur与其相加,并看与max之间的关系,如果比max大,更新max值
    //然后看cur是否为负,如果为负,则将其变为0,max不动,继续下一次循环
    //如果cur >= 0,直接进入下一次循环
    public static int subArrayMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            if (cur > max) {
                max = cur;
            }
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }


}
