package myCodePractice.zuo.basic.class07;

public class KnapSack {
    //给定两个数组w和v， 两个数组长度相等， w[i]表示第i件商品的
    //重量， v[i]表示第i件商品的价值。
    //再给定一个整数bag， 要求你挑选商品的重量加起来一定不能超
    //过bag， 返回满足这个条件下， 你能获得的最大价值。

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || bag < 0) return -1;

        return process(w, v, bag, 0, 0);
    }

    //递归函数功能:
    //传入数组,与限定条件
    //返回0~i-1位置上的商品已经选好,i位置及以后的商品进行选择的满足小于最大重量的最大价值
    public static int process(int[] w, int[] v, int bag, int i, int curW) {
        //base case
        //如果0~i-1位置上的商品已经超过了重量这条路是走不通的,直接返回最小值让这条路失效
        if (curW > bag) return Integer.MIN_VALUE;
        //如果走到了这里,说明没有超过重量,如果这条路已经走到头了(递归函数的功能为0~i-1位置已经选好,这里说明数组整体全选好了)
        //走到头了就返回0即可,因为没有可以加的了
        if (i == w.length) return 0;

        //递归函数功能就是返回一个最大值,所以不用在参数中传入当前价值
        return Math.max(process(w, v, bag, i + 1, curW), v[i] + process(w, v, bag, i + 1, curW + w[i]));
    }
}
