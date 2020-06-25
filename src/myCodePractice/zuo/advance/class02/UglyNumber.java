package myCodePractice.zuo.advance.class02;

public class UglyNumber {
    //规定1是丑数， 其他的数如果只含有2或3或5的因子， 那么这个
    //数也是丑数。
    //比如依次的丑数为： 1,2,3,5,6,8,9,10,12,15...
    //求第n个丑数

    //笨办法:首先除2，直到不能整除为止，然后除5到不能整除为止，
    // 然后除3直到不能整除为止。最终判断剩余的数字是否为1，
    // 如果是1则为丑数，否则不是丑数。

    //快速办法:第i个丑数一定可以由前面的丑数或*2,*3,*5得到
    //做法:三个指针,分别指向当前数*2.*3.*5,
    // 每次找下一个数都是三个指针乘指向的数中最小的那一个,
    // 每个指针和当前数乘完后向后移一个,
    // 如果有多个指针和当前指向数字相乘后都相等而且还都是最小值,
    // 那么这些指针在更新当前值后一起向后移动

    //实现笨办法
    public static int getNUglyNum1(int n) {
        if (n < 1) return -1;

        int numOfUgly = 1;
        int help = 1;
        while (numOfUgly < n) {
            if (isUgly(++help)) {
                numOfUgly++;
            }
        }
        return help;
    }

    public static boolean isUgly(int num)  {
        while (num % 5 == 0) {
            num /= 5;
        }
        while (num % 3 ==0) {
            num /= 3;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        return num == 1;
    }
    //实现快速方法:
    public static int getNUglyNum2(int n) {
        if (n < 1) return -1;

        int multi2 = 0;
        int multi3 = 0;
        int multi5 = 0;
        int[] res = new int[n];
        res[0] = 1;
        int helpMin = 0;
        int index = 1;

        //根据快速实现方法定义的规则,每次生成新的丑数一定是比之前的数字都大的
        //所以使用index来辅助res数组的生成
        while (index < n) {
            helpMin = Math.min(res[multi2] * 2, Math.min(res[multi3] * 3, res[multi5] * 5));
            res[index++] = helpMin;
            //这种写法可以保证如果有指针指向的数字乘上指针代表的数都是最小而且相等,两个指针一起向前动一下
            multi2 = res[multi2] * 2 == helpMin ? multi2 + 1 : multi2;
            multi3 = res[multi3] * 3 == helpMin ? multi3 + 1 : multi3;
            multi5 = res[multi5] * 5 == helpMin ? multi5 + 1 : multi5;
        }
        //index到了n-1后会++,这时不满足条件跳出
        //将其-1就是数组的最后一个位置上的数
        return res[index - 1];
    }

    //for test
    public static void main(String[] args) {
        int test = 11;
        System.out.println(getNUglyNum1(test));
        System.out.println(getNUglyNum2(test));
    }

}
