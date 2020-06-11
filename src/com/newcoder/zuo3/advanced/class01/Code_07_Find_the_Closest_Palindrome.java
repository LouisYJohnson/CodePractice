package com.newcoder.zuo3.advanced.class01;

public class Code_07_Find_the_Closest_Palindrome {
    //给一个字符串str， 代表一个整数， 找到除了这个数之外， 绝对值和这个数相差
    //最小的回文数。
    //例如：
    //str = “123”
    //返回“121”
    //注意：
    //假设字符串str一定能变成long类型
    //不能按照原来的数字做一个回文数字就完事了,比如特例199,回文数字191实际上是没有202距离199更近,
    // 所以需要找一个比数字大一个比数字小的总共两个回文数来与这个数比较
    //    具体的做法是先将原始的数求一个回文,然后看这个回文是比当前的数字大还是小还是相等
    //    相等:分别求比这个数字大,小的回文数
    //    大:求比这个数字小的回文数
    //    小:求比这个数字大的回文数
    //    回文都是用(判断进位或是借位之后的,进位要算上补0)对称轴前面(包括对称点计算得到的这个点,为什么?画个图就知道了)的做
    //          计算:offset + (chars.length -1 - offset) / 2 这个算出来的是索引值(从0开始的叫索引值),
    //          如果是偶数长度,对应上中位,如果是奇数长度,对应中心
    //    求大回文:
    //      如果数字的长度(除去算法添加的0)是偶数,那么从下中位(除去0的下中位的位置加1做回文,
    //      如果长度是奇数,从中间位置加1(注意这里的加一减一说的就是这个位置上的值!)做回文(算法实际在处理的时候,会在最前面补一个0(为了进位的时候好放数),
    //      但是实际上在找对称轴的时候还是忽略这个0数组中的这个位置)
    //    求小回文(减的时候是不需要预留0的,因为只有借位没有进位):
    //      如果数字的长度是偶数,那么从上中位的位置减1做回文,如果长度是奇数,从中间位置减1做回文
    //      如果减1之后最高位变成0,则直接返回除了最高位剩下位都为9的数
    public static String nearestPalindromic(String n) {
        if (n == null) return n;
        Long l = getRawPalindrome(n);
        Long num = Long.parseLong(n);
        if (num == l) {
            return getBigPalindrome(num) - num < num - getSmallPalindrome(num) ?
                    getBigPalindrome(num).toString() : getSmallPalindrome(num).toString();
        }else if (num < l) {//大:求比这个数字小的回文数
            return l - num < num - getSmallPalindrome(num) ?
                    l.toString() : getSmallPalindrome(num).toString();
        }else {//num > l 小:求比这个数字大的回文数
            return num - l < getBigPalindrome(num) - num ?
                    l.toString() : getBigPalindrome(num).toString();
        }
    }

    //求原始回文
    public static Long getRawPalindrome(String n) {
        char[] chars = n.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            chars[chars.length - 1 - i] = chars[i];
        }
        return Long.valueOf(String.valueOf(chars));
    }

    //求大回文
    public static Long getBigPalindrome(Long raw) {
        //为了进位需求,向char的第0个位置加入'0',将raw依次加入后面
        char[] chars = new char[raw.toString().length() + 1];
        chars[0] = '0';
        char[] help = raw.toString().toCharArray();
        for (int i = 1; i < chars.length; i++) {
            chars[i] = help[i - 1];
        }
        //先看要做回文的位置是否因为要借位而发生改变
        //这种中心位置的计算方法,实际上对于奇数长度算的就是重点,偶数长度算的是上中位,if else可以合成一句
        //如果数字的长度(除去算法添加的0)是奇数,先在中间位置+1看是否进位然后再做回文
        if ((raw.toString().length() & 1) == 1) {
            for (int i = 1 + (chars.length - 1) / 2; i >= 0; i--) {
                if (++chars[i] > '9') {//这样做,每个位置上都+1了还能顺便判断(判断条件中的语句是会执行的,而且++会改变自身的值,女少口阿!)
                    chars[i] = '0';
                }else {
                    break;
                }
            }
        }else {//如果数字的长度(除去算法添加的0)是偶数,那么从下中位(除去0的下中位的位置加1做回文
            for (int i = 1 + (chars.length - 1) / 2 + 1; i >= 0; i--) {
                if (++chars[i] > '9') {//这样做,每个位置上都+1了还能顺便判断(判断条件中的语句是会执行的,而且++会改变自身的值,女少口阿!)
                    chars[i] = '0';
                }else {
                    break;
                }
            }
        }
        //对数组做回文,做回文的时候要判断用来进位的0用了没有,如果没用,做回文的时候要删掉
        //回文都是用对称轴前面的做()
        int offset = chars[0] == '0' ? 1 : 0;
        char[] result = new char[chars.length - offset];
        int j = 0;
        for (int i = offset; i < result.length; i++) {
            result[j++] = chars[i];
        }
        int center = (result.length / 2);
        for (int i = 0; i <= center; i++) {
            result[result.length - 1 - i] = result[i];
        }
        //
        return Long.valueOf(String.valueOf(result));
    }

    //求小回文(减的时候是不需要预留0的,因为只有借位没有进位):
    //      如果数字的长度是偶数,那么从上中位的位置减1做回文,如果长度是奇数,从中间位置减1做回文
    //      如果减1之后最高位变成0,则直接返回除了最高位剩下位都为9的数
    public static Long getSmallPalindrome(Long raw) {
        char[] chars = raw.toString().toCharArray();
        //如果长度是奇数,从中间位置减1做回文
        //如果数字的长度是偶数,那么从上中位的位置减1做回文,
        //先看要做回文的位置是否因为要借位而发生改变
        int center = (chars.length - 1) / 2;
        for (int i = center; i >= 0; i--) {
            if(--chars[i] < '0') {
                chars[i] = '9';
            }else {
                break;
            }
        }

        //如果减1之后最高位变成0,则直接返回除了最高位剩下位都为9的数,否则,在对称轴处做回文
        if (chars[0] == '0') {
            char[] result = new char[chars.length - 1];
            for (int i = 0; i < result.length; i++) {
                result[i] = '9';
            }
            return Long.parseLong(result.toString());
        }else {
            for (int i = 0; i < (chars.length - 1) / 2; i++) {
                chars[chars.length - 1 - i] = chars[i];
            }
            return Long.parseLong(chars.toString());
        }
    }

    //for test
    public static void main(String[] args) {
        String s = "199";
        System.out.println(nearestPalindromic(s));
    }
}
