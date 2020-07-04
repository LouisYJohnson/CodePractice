package myCodePractice.zuo.advance.class06;

import java.util.Arrays;

public class SubArrayMaxProduct {
    //数组中子数组的最大累乘积
    //【题目】
    //给定一个double类型的数组arr， 其中的元素可正、 可负、 可0， 返回
    //子数组累乘的最大乘积。 例如， arr=[-2.5， 4， 0， 3， 0.5， 8， -1]，
    //子数组[3， 0.5， 8]累乘可以获得最大的乘积12， 所以返回12。

    //预处理数组:
    //遍历数组的每一个位置,每一个位置上填的值就是必须以这个位置上的数为结尾的情况下得到的最大乘积
    //i位置需要i-1位置上的max,min,才能得到这个位置上的max,min
    //每个位置上需要两个信息,下一个位置才能继续往下走
    //  可能性1:不要前面的数,只要i
    //  可能性2:要前面的数,i位置上的最大累乘积是i-1位置上的最大累乘积乘i位置得到的
    //  可能性3:i-1位置上的最小累乘积乘i位置上的可能得到最大累乘积
    //
    //算到最后,每个位置上最大的max就是答案

    public static double getSubArrayMaxProduct(double[] arr) {
        if (arr == null || arr.length == 0) return Double.MIN_VALUE;
        if (arr.length == 1) return arr[0];

//        double helpMaxValue = arr[0];
//        double helpMinValue = arr[0];
        double[] helpMaxs = new double[arr.length];
        double[] helpMins = new double[arr.length];
        helpMaxs[0] = arr[0];
        helpMins[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            //必须包含当前位置i,可以选择与必须包含i-1位置得到的最大,最小值相乘,或者不要之前(必须以i-1位置结尾的最大值)的结果,只要自己,看哪个最大/最小
            //最后,返回这个数组中的最大值即为结果
            helpMaxs[i] = Math.max(arr[i], Math.max(arr[i] * helpMaxs[i - 1], arr[i] * helpMins[i - 1]));
            helpMins[i] = Math.min(arr[i], Math.min(arr[i] * helpMaxs[i - 1], arr[i] * helpMins[i - 1]));
        }
        Arrays.sort(helpMaxs);
        return helpMaxs[helpMaxs.length - 1];
    }

    public static void main(String[] args) {
        double[] arr = {-2.5, 4, 0, 3, 0.5, 8, -1};
        System.out.println(getSubArrayMaxProduct(arr));
    }
}
