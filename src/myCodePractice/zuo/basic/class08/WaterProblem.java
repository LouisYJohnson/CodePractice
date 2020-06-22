package myCodePractice.zuo.basic.class08;

public class WaterProblem {
    //给定一个数组代表一个容器，
    //比如[3,1,2,4]，
    //代表0位置是一个宽度为1， 高度为3的直方图。
    //代表1位置是一个宽度为1， 高度为1的直方图。
    //代表2位置是一个宽度为1， 高度为2的直方图。
    //代表3位置是一个宽度为1， 高度为4的直方图。
    //所有直方图的底部都在一条水平线上， 且紧靠着。
    //把这个图想象成一个容器， 这个容器可以装3格的水。
    //给定一个没有负数的数组arr， 返回能装几格水？

    //不要想只找小的波谷,因为有可能会出现大的波峰,小的波谷就没用了,所以只看单个格子上面能装多少水:
    //一个格子高度为5,左侧最大值为10右侧最大值为20,则最多能装5个格子的水,但是这样的话,
    // 每个格子都要左右查找最大值和最小值,时间复杂度高,所以使用预处理数组的技巧,来减少时间复杂度

    public static int waterProblem(int[] arr) {
        if (arr == null || arr.length < 3) return 0;

        int[] helpLeftMax = new int[arr.length];
        int[] helpRightMax = new int[arr.length];

        int help = Integer.MIN_VALUE;
        for (int i = 0; i < helpLeftMax.length; i++) {
            if (help < arr[i]) {
                helpLeftMax[i] = arr[i];
                help = arr[i];
            } else {
                helpLeftMax[i] = help;
            }
        }
        help = Integer.MIN_VALUE;
        for (int i = helpRightMax.length - 1; i >= 0; i--) {
            if (help < arr[i]) {
                helpRightMax[i] = arr[i];
                help = arr[i];
            } else {
                helpRightMax[i] = help;
            }
        }

        int res = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            res += Math.max(Math.min(helpLeftMax[i], helpRightMax[i]) - arr[i], 0);
        }
        return res;
    }

}
