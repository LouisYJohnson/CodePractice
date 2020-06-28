package myCodePractice.zuo.advance.class03;

public class ConvertStringToInteger {
    //将整数字符串转成整数值
    //【题目】
    //给定一个字符串str， 如果str符合日常书写的整数形式， 并且属于32
    //位整数的范围， 返回str所代表的整数值， 否则返回0。
    //【举例】
    //str="123"， 返回123。
    //str="023"， 因为"023"不符合日常的书写习惯， 所以返回0。
    //str="A13"， 返回0。
    //str="0"， 返回0。
    //str="2147483647"， 返回2147483647。
    //str="2147483648"， 因为溢出了， 所以返回0。
    //str="-123"， 返回-123。

    //先判断是否是有效的数,然后再转成整数
    //在任何语言中,负数的最小值的绝对值总是比整数的最大值大1,所以这种转换的题,
    // 都是按照负数转换,然后根据需要转成正数或者负数,如果说等于负数的最小还要正数,
    // 那就溢出了,如果按照正数转的话,负数的最小是转不过来的,
    // 而且正数的最大如果是负数最小的绝对值,也不知道溢出了
    public static Integer convertStringToInteger(String string) {
        if (string == null || string.length() == 0) return null;

        char[] chars = string.toCharArray();
        //先检测字符串是否合法
        if (!isValid(chars)) return 0;
        //然后将字符数组转化为整数(按照负数转,转完看要正数还是负数)
        int res = 0;
        int help = -1;
        int offset = chars[0] == '-' ? 1 : 0;
        for (int i = chars.length - 1; i >= offset; i--) {
            //本次变化的值必须能够承受不越界,才允许变化!
            if (Integer.MIN_VALUE - res <= (chars[i] - '0') * help) {
                res += (chars[i] - '0') * help;
                help *= 10;
            }else {
                return 0;
            }
        }
        if (offset == 1) {
            return res;
        }else {
            if (res == Integer.MIN_VALUE) {
                return 0;
            }else {
                return -res;
            }
        }
    }

    public static boolean isValid(char[] chars) {
        if (chars.length == 1 && chars[0] == '0') return true;
        if (chars.length > 1 && chars[0] == '0') return false;
        if (chars.length > 1 && chars[0] == '-' || (chars[0] > '0' && chars[0] <= '9')) {
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] < '0' || chars[i] > '9') {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(convertStringToInteger("023"));
        System.out.println(convertStringToInteger("2147483647"));
        System.out.println(convertStringToInteger("2147483648"));
        System.out.println(convertStringToInteger("-123"));
        System.out.println(convertStringToInteger("-2147483648"));
    }

}
