package leetcode.topinterview;

import java.util.HashMap;

public class LC454FourSumII {
    //给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
    //为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
    // 所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
    //例如:
    //输入:
    //A = [ 1, 2]
    //B = [-2,-1]
    //C = [-1, 2]
    //D = [ 0, 2]
    //输出:
    //2
    //解释:
    //两个元组如下:
    //1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
    //2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/4sum-ii
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    class Solution {
        //思路：
        //一.采用分为两组，HashMap存一组，另一组和HashMap进行比对。
        //二.这样的话情况就可以分为三种：
        //1.HashMap存一个数组，如A。然后计算三个数组之和，如BCD。时间复杂度为：O(n)+O(n^3),得到O(n^3).
        //2.HashMap存三个数组之和，如ABC。然后计算一个数组，如D。时间复杂度为：O(n^3)+O(n),得到O(n^3).
        //3.HashMap存两个数组之和，如AB。然后计算两个数组之和，如CD。时间复杂度为：O(n^2)+O(n^2),得到O(n^2).
        //三.根据第二点我们可以得出要存两个数组算两个数组。
        //四.我们以存AB两数组之和为例。首先求出A和B任意两数之和sumAB，以sumAB为key，sumAB出现的次数为value，存入hashmap中。
        //然后计算C和D中任意两数之和的相反数sumCD，在hashmap中查找是否存在key为sumCD。
        //算法时间复杂度为O(n2)。
        //作者：guo-sheng-fei
        //链接：https://leetcode-cn.com/problems/4sum-ii/solution/chao-ji-rong-yi-li-jie-de-fang-fa-si-shu-xiang-jia/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            HashMap<Integer, Integer> sumABMap = new HashMap<>();
            HashMap<Integer, Integer> sumCDRevMap = new HashMap<>();
            //循环遍历AB,找到每个和出现的次数放入sumABMap中
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    int tempSumAB = A[i] + B[j];
                    if (!sumABMap.containsKey(tempSumAB)) {
                        sumABMap.put(tempSumAB, 1);
                    }else {
                        sumABMap.put(tempSumAB, sumABMap.get(tempSumAB) + 1);
                    }
                }
            }

            int res = 0;
            //遍历C,D中的所有可能,只要符合条件,就将结果和ABMap中符合条件的结果进行相加
            //因为AB中有几个相同结果的和,CD中符合结果的和就可以和相同结果的和结合几次
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < D.length; j++) {
                    int tempSumCDRev = -(C[i] + D[j]);
                    if (sumABMap.containsKey(tempSumCDRev)) {
                        res += sumABMap.get(tempSumCDRev);
                    }
                }
            }
            return res;
//            //下面这种做法是错的,其实HashMap的构建只需要一次就可以了
//            //在C,D进行运算的时候,只要碰到一次相反数和sumABMap中一样,就将结果与res相加
//            //这是因为每次遇到一个C,D中的结果,就可以和sumABMap中的出现次数组合出现次数次
//            //而不是两个Map都构建完毕后,再分别遍历
//            //循环遍历CD,找到每个和的相反数出现的次数放入sumCDRevMap中,然后看是否有相同的即可
//            //下面的做法是分别计算和出现的次数,然后让符合条件的和进行相乘,但是这种做法不管是时间还是空间复杂度都比较高
//            for (int i = 0; i < C.length; i++) {
//                for (int j = 0; j < D.length; j++) {
//                    int tempSumCDRev = -(C[i] + D[j]);
//                    if (!sumCDRevMap.containsKey(tempSumCDRev)) {
//                        sumCDRevMap.put(tempSumCDRev, 1);
//                    }else {
//                        sumCDRevMap.put(tempSumCDRev, sumCDRevMap.get(tempSumCDRev) + 1);
//                    }
//                }
//            }
//            //循环遍历sumABMap,然后看sumCDRevMap中有没有对应的相反数
//            int res = 0;
//            for (Integer integer : sumABMap.keySet()) {
//                for (Integer integer1 : sumCDRevMap.keySet()) {
//                    if (integer.equals(integer1)) {
//                        res += sumABMap.get(integer) * sumCDRevMap.get(integer1);
//                    }
//                }
//            }
//            return res;
        }
    }
}
