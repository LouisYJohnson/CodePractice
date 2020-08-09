package leetcode.topinterview;

public class LC204CountPrimes {
    //统计所有小于非负整数 n 的质数的数量。
    //示例:
    //输入: 10
    //输出: 4
    //解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
    class Solution {
        //1、目前使用较有效的方法是试除法。
        // 用试除法判断一个自然数a是不是素数时，
        // 用各个素数从小到大依次去除a，
        // 如果到某一个素数正好整除，这个a就可以断定不是素数；
        // 如果不能整除，当不完全商又小于这个素数时，就不必再继续试除，
        // 可以断定a必然是素数。
        //2、素数又称质数。一个大于1的自然数，
        // 除了1和它本身外，不能被其他自然数整除，
        // 换句话说就是该数除了1和它本身以外不再有其他的因数；
        // 否则称为合数。
        public int countPrimes(int n) {
            //厄拉多塞筛法. 比如说求20以内质数的个数,首先0,1不是质数.
            // 2是第一个质数,然后把20以内所有2的倍数划去.
            // 2后面紧跟的数即为下一个质数3,然后把3所有的倍数划去.
            // 3后面紧跟的数即为下一个质数5,再把5所有的倍数划去.以此类推.
            int result = 0;
            //false为质数标记
            //因为是小于n的质数数量,所以数组长度为n,下标就代表数值了也就是0~n-1
            boolean[] b = new boolean[n];
            if (2 < n) {    //如果大于2则一定拥有2这个质数
                result++;
            }
            //直在奇数中查找素数,并且每次都将奇数的所有奇数倍的数字都置为非质数(奇数乘奇数必为奇数
            // 而且这个奇数一定不是素数,因为有除了自身和1之外的因子,奇数乘偶数为偶数)
            for (int i = 3; i < n; i += 2) {    //从3开始遍历,并且只遍历奇数
                if (!b[i]) {    //是质数
                    for (int j = 3; i * j < n; j += 2) {    //将当前质数的奇数倍都设置成非质数标记,1没必要,因为1乘任何数都是1
                        b[i * j] = true;
                    }
                    result++;
                }
            }
            return result;
        }
    }

    //如果是挨个数字筛选:
    //换句话说，如果在 [2,sqrt(n)] 这个区间之内没有发现可整除因子，
    // 就可以直接断定 n 是素数了，因为在区间 [sqrt(n),n] 也一定不会发现可整除因子。
    // 判断整数 n 是否是素数
    boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }
}