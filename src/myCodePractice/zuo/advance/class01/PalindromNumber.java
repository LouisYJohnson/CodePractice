package myCodePractice.zuo.advance.class01;

public class PalindromNumber {
    //给定一个整数， 判断该数是否是回文数
    public static boolean isPalindromNumber(int num) {
        //先获取num的最高位
        int help = 1;
        while (help * 10 < num) {
            help *= 10;
        }
        //输入的数可能是2位数,所以要先判断一次
        //不然进不了while循环
        int high = num / help;
        int low = num % 10;
        if (high != low) return false;
        num = num - high * help;
        num = num / 10;
        help /= 100;

        while (help / 100 != 0) {
            high = num / help;
            low = num % 10;
            if (high != low) return false;
            num = num - high * help;
            num = num / 10;
            help /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromNumber(1111));
    }

}
