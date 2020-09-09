package myCodePractice.zuo.advance.class04;

public class AddMinusMultiDivideByBit {
    //题目一
    //只用位运算不用算术运算实现整数的加减乘除运算
    //【题目】
    //给定两个32位整数a和b， 可正、 可负、 可0。 不能使用算术运算
    //符， 分别实现a和b的加减乘除运算。
    //【要求】
    //如果给定的a和b执行加减乘除的某些结果本来就会导致数据的
    //溢出， 那么你实现的函数不必对那些结果负责

    //使用位运算实现+运算
    //方法:
    //  需要进位信息:两个数按位与
    //  需要非进位信息:两个数按位异或
    //然后让进位信息与非进位信息再相加(重复刚才流程,提取这两个数的进位信息与非进位信息)
    //直到不产生进位信息的时候(进位信息为0),无进位信息就是结果
    public static int add(int a, int b) {
        int forwardInfo = a & b;
        int unForwardInfo = a ^ b;
        int helpUnForwardInfo = unForwardInfo;

        //两个变量需要依靠彼此更新,在更新一个变量的时候必须做一个备份
        //否则在更新另一个变量的时候之前的变量已经变了,更新错误
        while (forwardInfo != 0) {
            forwardInfo = forwardInfo << 1;
            unForwardInfo = forwardInfo ^ unForwardInfo;
            forwardInfo = forwardInfo & helpUnForwardInfo;
            helpUnForwardInfo = unForwardInfo;
        }
        return unForwardInfo;
    }
    //使用位运算实现-运算
    //方法:
    //  减法就是加相反数,还是用+的方法,只是将后面的数变成自身的相反数
    //变负数方法:按位取反+1
    public static int minus(int a, int b) {
        b = ~b;
        b = add(b, 1);
        return add(a, b);
    }
    //使用位运算实现×运算
    //方法:
    //  举例:列一个竖式
    //            1 0 1 0
    //          × 0 1 0 1
    //          ----------
    //            1 0 1 0
    //          0 0 0 0
    //        1 0 1 0
    //      0 0 0 0
    //      ---------------
    //就变成了这四个数相加的过程
    //每次乘数按位右移一位,如果最低位是1,就将被乘数左移当前右移的位数后加到结果中
    //直到乘数移成0为止
    public static int multi(int a, int b) {
        int res = 0;
        //记录当前b右移的位数
        int helpMovePos = 0;

        while (b != 0) {
            if ((b & 1) == 1) {
                res = add(res, a << helpMovePos);
            }
            helpMovePos++;
            b = b >>> 1;
        }
        return res;
    }
    //用位运算实现除运算
    //方法:
    //  例:a/b=7,则a=b*7=(2^0+2^1+2^2)*b即a-(2^0+2^1+2^2)*b=0
    //核心就是在找被除数减去多少倍的除数结果为0,那个倍数就是要求的结果
    //但是这种方法的前提是除数与被除数都是正数,所以要加入一个判断
    public static int divide(int a, int b) {
        if (b == 0) {
            throw  new RuntimeException("divisor is 0");
        }else if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        }else if (b == Integer.MIN_VALUE) {
            return 0;
        }else if (a == Integer.MIN_VALUE) { //如果a是负数最小值,通过正数的方法是减不出来的,必须使用如下公式来解
            //因为负数的最小值为负的2的-31次幂,正数的最大值为2的31次幂减1
            //所以使用正数来做除法的话,从2的30次幂开始减,如果a是负数最小值,变成正数会溢出,
            // 就算没有溢出(不可能),减了2的30次幂,2的低于30次幂的数的和仍然小于2的31次幂
            //综上:必须使用如下公式
            int temp = div(add(a, 1), b);
            return add(temp, div(minus(a, multi(temp, b)), b));
        }else {
            return div(a, b);
        }
    }

    //除法(看什么时候被除数能减成0,要用正数来做,然后根据除数与被除数的符号判断结果是正数还是负数)
    public static int div(int a, int b) {
        int helpA = a < 0 ? negNum(a) : a;
        int helpB = b < 0 ? negNum(b) : b;
        int helpMovePos = 30;
        int res = 0;

        while (helpA != 0 && helpMovePos >= 0) {
            if (minus(helpA, helpB << helpMovePos) >= 0) {
                helpA = minus(helpA, helpB << helpMovePos);
                res = add(res, 1 << helpMovePos);
            }
            helpMovePos = minus(helpMovePos, 1);
        }
        //使用异或运算来判断返回结果为正还是负
        return res = (a < 0) ^ (b < 0) ? negNum(res) : res;
    }
    //将输入的数通过位运算变为相反数
    public static int negNum(int num) {
        num = ~num;
        num = add(num, 1);
        return num;
    }


    public static void main(String[] args) {
        System.out.println(add(19,21));
        System.out.println(minus(1,5));
        System.out.println(multi(1,295));
        System.out.println(divide(Integer.MIN_VALUE,2));
    }

}
