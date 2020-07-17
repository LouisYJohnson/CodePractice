package myCodePractice.zuo.advance.class01;


import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class FindTheClosestPalindrome {
    //给一个字符串str， 代表一个整数， 找到除了这个数之外， 绝对值和这个数相差
    //最小的回文数。
    //例如：
    //str = “123”
    //返回“121”
    //注意：
    //假设字符串str一定能变成long类型

    //不能按照原来的数字做一个回文数字就完事了,比如特例199,
    // 回文数字191实际上是没有202距离199更近,
    // 所以需要找一个比数字大一个比数字小的总共两个回文数来与这个数比较

    //具体的做法是先将原始的数求一个回文,然后看这个回文是比当前的数字大还是小还是相等
    //相等:分别求比这个数字大,小的回文数
    //大:求比这个数字小的回文数
    //小:求比这个数字大的回文数

    //回文都是用对称轴前面的做

    //求大回文:
    //如果数字的长度(除去算法添加的0)是偶数,
    // 那么从下中位(除去0的下中位)的位置加1做回文,
    // 如果长度是奇数,从中间位置加1(指该位置上的元素+1做回文,不是位置坐标+1)做回文
    // (算法实际在处理的时候,会在最前面补一个0(为了进位的时候好放数),
    // 但是实际上对应的还是忽略这个0数组中的这个位置)

    //求小回文(减的时候是不需要预留0的,因为只有借位没有进位):
    //如果数字的长度是偶数,那么从上中位的位置减1做回文,如果长度是奇数,从中间位置减1做回文
    //如果减1之后最高位变成0,则直接返回除了最高位剩下位都为9的数,否则直接做回文


    //具体的做法是先将原始的数求一个回文,然后看这个回文是比当前的数字大还是小还是相等
    //相等:分别求比这个数字大,小的回文数,结果在这两个数之间选
    //大:求比这个数字小的回文数,结果在小回文和原回文之间选
    //小:求比这个数字大的回文数,结果在大回文和原回文之间选

    //大小回文都是对原始字符串求的
    //0 1 2 3 4
    //长度为奇数,直接取中间位置,2
    //0 1 2 3
    //长度为偶数,下中位位于位置1,上中位位于位置2

    public static String findClosestPalindromNum(String str) {
        if (str == null) return null;

        String res = null;
        long rawNum = Long.valueOf(str);
        //获取原始字符串对应的回文数
        long oriStrPal = getOriStrPal(str);
        //判断二者之间的关系
        if (rawNum == oriStrPal) {
            long smallPal = getSmallPal(str);
            long bigPal = getBigPal(str);
            //绝对值更小的返回,所以哪边值小返回哪边
            return rawNum - smallPal < bigPal - rawNum ? String.valueOf(smallPal) : String.valueOf(bigPal);
        }else if (oriStrPal > rawNum) {
            long smallPal = getSmallPal(str);
            return rawNum - smallPal < Math.abs(rawNum - oriStrPal) ? String.valueOf(smallPal) : String.valueOf(oriStrPal);
        }else {
            long bigPal = getBigPal(str);
            return bigPal - rawNum < Math.abs(rawNum - oriStrPal) ? String.valueOf(bigPal) : String.valueOf(oriStrPal);
        }
    }

    //求原始回文
    //回文都是用对称轴前面的做
    public static long getOriStrPal(String str) {
        char[] numChar = str.toCharArray();
        int mid = 0 + (str.length() - 1 - 0) / 2;

        for (int i = 0; i <= mid; i++) {
            numChar[str.length() - 1 - i] = numChar[i];
        }
        return Long.valueOf(String.valueOf(numChar));
    }

    //求大回文:
    //如果数字的长度(除去算法添加的0)是偶数,
    // 那么从下中位(除去0的下中位)的位置加1做回文,
    // 如果长度是奇数,从中间位置加1(指该位置上的元素+1做回文,不是位置坐标+1)做回文
    // (算法实际在处理的时候,会在最前面补一个0(为了进位的时候好放数),
    // 但是实际上在做回文的时候,如果添加的这个0并没有被进位的数进1还是忽略这个0数组中的这个位置)
    public static long getBigPal(String str) {
        char[] processChar = new char[str.length() + 1];
        char[] strChar = str.toCharArray();

        //processChar就是在前面加上0
        processChar[0] = '0';
        for (int i = 0; i < str.length(); i++) {
            processChar[i + 1] = strChar[i];
        }
        //除去0位置的下中位上或者中间位置上+1并进位
        int lowMid = (str.length() & 1) == 1 ? 1 + (processChar.length - 1 - 1) / 2 : 1 + (processChar.length - 1 - 1) / 2 + 1;
        for (int i = lowMid; i >= 0; i--) { //数组的前面才是数字的高位
            if (++processChar[i] > '9') {   //在if判断的同时进行进位相加操作
                processChar[i] = '0';   //进位操作会被传递到高位上去
            }else {
                break;  //如果没有进位,直接结束
            }
        }
        //看添加的0到底通过进位用上了没有,如果没用上,在做回文的时候直接去掉
        //如果用上了,做回文的时候它也算在内
        int offSet = processChar[0] == '0' ? 1 : 0;
        char[] res = new char[processChar.length - offSet];
        int index = 0;
        //将结果写入res中
        for (int i = offSet; i < processChar.length; i++) {
            res[index++] = processChar[i];
        }
        //对res做回文
        int mid = 0 + (res.length - 1 - 0) / 2;
        for (int i = 0; i <= mid; i++) {
            res[res.length - 1 - i] = res[i];
        }
        return Long.valueOf(String.valueOf(res));
    }

    //求小回文(减的时候是不需要预留0的,因为只有借位没有进位):
    //如果数字的长度是偶数,那么从上中位的位置减1做回文,如果长度是奇数,从中间位置减1做回文
    //如果减1之后最高位变成0,则直接返回除了最高位剩下位都为9的数,否则直接做回文
    public static long getSmallPal(String str) {
        char[] processChar = str.toCharArray();
        char[] res = null;

        int upMid = (str.length() & 1) == 1 ? 0 + (str.length() - 0) / 2 : 0 + (str.length() - 0) / 2 + 1;
        for (int i = upMid; i >= 0; i--) {
            if (--processChar[i] < '0') {
                processChar[i] = '9';
            }else {
                break;
            }
        }
        //如果减1之后最高位变成0,则直接返回除了最高位剩下位都为9的数,
        if (processChar[0] == '0') {
            res = new char[processChar.length - 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = '9';
            }
            return Long.valueOf(String.valueOf(res));
        }
        //否则直接做回文
        int mid = 0 + (processChar.length - 1 - 0) / 2;
        for (int i = 0; i <= mid; i++) {
            processChar[processChar.length - 1 - i] = processChar[i];
        }
        return Long.valueOf(String.valueOf(processChar));
    }

    //for Combinations
    public static String nearestPalindromic(String n) {
        Long num = Long.valueOf(n);
        Long raw = getRawPalindrome(n);
        Long big = raw > num ? raw : getBigPalindrome(raw);
        Long small = raw < num ? raw : getSmallPalindrome(raw);
        return String.valueOf(big - num >= num - small ? small : big);
    }

    public static Long getRawPalindrome(String n) {
        char[] chs = n.toCharArray();
        int len = chs.length;
        for (int i = 0; i < len / 2; i++) {
            chs[len - 1 - i] = chs[i];
        }
        return Long.valueOf(String.valueOf(chs));
    }

    public static Long getBigPalindrome(Long raw) {
        char[] chs = String.valueOf(raw).toCharArray();
        char[] res = new char[chs.length + 1];
        res[0] = '0';
        for (int i = 0; i < chs.length; i++) {
            res[i + 1] = chs[i];
        }
        int size = chs.length;
        for (int j = (size - 1) / 2 + 1; j >= 0; j--) {
            if (++res[j] > '9') {
                res[j] = '0';
            } else {
                break;
            }
        }
        int offset = res[0] == '1' ? 1 : 0;
        size = res.length;
        for (int i = size - 1; i >= (size + offset) / 2; i--) {
            res[i] = res[size - i - offset];
        }
        return Long.valueOf(String.valueOf(res));
    }

    public static Long getSmallPalindrome(Long raw) {
        char[] chs = String.valueOf(raw).toCharArray();
        char[] res = new char[chs.length];
        int size = res.length;
        for (int i = 0; i < size; i++) {
            res[i] = chs[i];
        }
        for (int j = (size - 1) / 2; j >= 0; j--) {
            if (--res[j] < '0') {
                res[j] = '9';
            } else {
                break;
            }
        }
        if (res[0] == '0') {
            res = new char[size - 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = '9';
            }
            return size == 1 ? 0 : Long.parseLong(String.valueOf(res));
        }
        for (int k = 0; k < size / 2; k++) {
            res[size - 1 - k] = res[k];
        }
        return Long.valueOf(String.valueOf(res));
    }

    //for Combinations
    public static void main(String[] args) {
        String s = "199";
        System.out.println(findClosestPalindromNum(s));
        System.out.println(nearestPalindromic(s));
    }
}
