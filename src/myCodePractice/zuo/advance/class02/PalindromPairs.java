package myCodePractice.zuo.advance.class02;

import com.sun.org.apache.bcel.internal.generic.FSUB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PalindromPairs {
    //数组words中都是不同的词， 如果其中str1加str2之后是回文串，
    //则str1的位置和str2的位置我们需要收集。
    //比如
    //words = ["bat", "tab", "cat"]
    //返回[[0, 1], [1, 0]]
    //words = ["abcd", "dcba", "lls", "s", "sssll"]
    //返回[[0, 1], [1, 0], [3, 2], [2, 4]]

    //分析:
    //  对于一个字符串包括三种情况:
    //      1:数组中存在该字符串的逆序,加在左右都可以形成回文串
    //      2:该字符串中本身存在回文串(必须包括开头,开头只有一个字符也算),那么查找除了开头回文的剩余逆序在字符串数组中是否存在即可
    //      3:必须包括结尾,其他同上
    //所以一个字符串要返回的可能对应了1个,2个或者3个结果,所以使用List来存放最好

    //对应了下面三种方法:
    //1.直接找一个字符串的逆序,然后看逆序的在字典中有没有,如果有,二者在一起绝对是回文

    //2.manacher算法改写:找到所有必须包含第一个字符的回文子串
    // (从头开始找必须包含第一个字符的回文子串,最多也就到中点了),
    // 后面的逆序,然后去链表中找后面的逆序,比如121345,
    // 在链表中找:必须包含第一个字符的回文子串:1,121
    //21345的逆序54312
    //345的逆序543如果有,就证明可以:543121345

    //3.反过来,仍然是manacher算法改写:
    // 找到所有必须包含最后一个字符的回文子串,
    // 然后去链表中找前面不是回文子串的逆序

    public static List<List<Integer>> palindromePairs(String[] strings) {
        if (strings == null || strings.length == 0) return null;

        List<List<Integer>> res = new ArrayList<>();
        HashMap<String, Integer> stringIndexMap = getStringIndexMap(strings);
        for (int i = 0; i < strings.length; i++) {
            //按照指定 collection 的迭代器所返回的元素顺序，将该 collection 中的所有元素添加到此列表的尾部。
            //即将List<Integer>添加到List<List<Integer>>的最外层List中
            res.addAll(findStrAll(strings[i], i, stringIndexMap));
        }
        return res;
    }

    //想要实现快速查找是否存在逆序的字符串,使用HashMap实现
    //key为对应字符串,value为字符串在字符数组中对应的下标
    //题目中说了wrods中都是不同的词,可以放心使用HashMap不必担心同一个key覆盖value的问题
    public static HashMap<String, Integer> getStringIndexMap(String[] strings) {
        if (strings == null || strings.length == 0) return null;

        HashMap<String, Integer> stringIndexMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            stringIndexMap.put(strings[i], i);
        }
        return stringIndexMap;
    }

    //所以一个字符串要返回的可能对应了1个,2个或者3个结果,所以使用List来存放最好
    //本方法实现查找一个str在str[]中的所有能够形成回文的元素,并将成对的下标添加到一个List中返回
    //这里记得要查重,自己和自己是不能组合的
    public static List<List<Integer>> findStrAll(String str, int strIndex, HashMap<String, Integer> strIndexMap) {
        ArrayList<List<Integer>> res = new ArrayList<>();

        char[] str2Chars = str.toCharArray();
        String strBackWard = strBackward(str2Chars);
        //查找自身的逆序,如果存在,将传入字符串下标与传入字符串逆序的下标包装为一个List,存在res中
        //要加查重,自身和自身的逆序不算
        if (strIndexMap.containsKey(strBackWard) && strIndexMap.get(strBackWard) != strIndex) {
            res.add(addRecord(strIndex, strIndexMap.get(strBackWard)));
        }
        //manacher算法改写,找到所有包含第一个字符的回文串,并查找每一个包含第一个字符回文串其余部分在数组中是否存在
        //就是计算pArr数组,找包括第一个字符的回文串,最多就到manacherChar的中间位置(i从1开始每次+2条件为<=mid可以避开#的位置)
        char[] manacherChars = getManacherChars(str2Chars);
        int[] pArr = getManacherPArr(manacherChars);
        //经过处理的manacherChar长度必为奇数,所以mid必指向中心(有可能是#,也有可能是字符)
        int mid = 0 + (manacherChars.length - 1 - 0) / 2;

        //这个位置不能跳2,跳了的话,比如"||s"这个字符串找不到长度为2的回文"||"
        //实际上我们要的并不是每个位置i的坐标,我们只是要pArr[i]位置上满足条件的回文半径,这些半径与i位置上的字符是什么无关
        //因为manacher算法中,即使manacherChar当前位置上的元素为#,也是对应着原字符串中的回文串长度的(之所以要除去manahcerChar第0个元素
        // 就是因为第0个元素必为#,并且pArr[0]必为1,但是并不合格,所以去掉)
        //而是对应了原字符串中的回文半径
        //我们要的是必须包括第一个字符的回文串长度,这样才能确定逆序应该从哪里开始
        for (int i = 1; i <= mid; i++) {
            //如果包含第一个字符的话,i - pArr[i] == -1
            if (i - pArr[i] == -1) {
                //如果包含第一个字符,i位置开始还是回文串,那么对应到原字符串中的位置为:i/2 - 1
                //但是这里不用对应到原字符串中的位置,而是要找到包含第一个字符的回文串结束位置
                //pArr[i] - 1即为i位置上的回文串长度,这个长度就是开始找逆序的起始位置
                //在此处加入查重,不能自己和自己回文
                if (strIndexMap.containsKey(strBackWard.substring(0, str.length() - (pArr[i] - 1))) &&
                        strIndexMap.get(strBackWard.substring(0, str.length() - (pArr[i] - 1))) != strIndex) {
                    res.add(addRecord(strIndex, strIndexMap.get(strBackWard.substring(0, str.length() - (pArr[i] - 1)))));
                }
            }
        }

        for (int i = pArr.length - 2; i >= mid; i--) {
            if (i + pArr[i] == pArr.length) {
                if (strIndexMap.containsKey(strBackWard.substring(pArr[i] - 1)) &&
                        strIndexMap.get(strBackWard.substring(pArr[i] - 1)) != strIndex) {
                    res.add(addRecord(strIndex, strIndexMap.get(strBackWard.substring(pArr[i] - 1))));
                }
            }
        }

        return res;
    }

    //本方法用于返回自身的逆序字符串
    //从头到mid进行swap操作即可
    public static String strBackward(char[] str2Char) {
        //这种写法不行,只是让一个变量指向同样的内存区域,并不会创建两个数组
//        char[] res = str2Char;
        char[] res = str2Char.clone();
        int mid = 0 + (str2Char.length - 1 - 0) / 2;
        for (int i = 0; i <= mid; i++) {
            swap(res, i, res.length - 1 - i);
        }
        return String.valueOf(res);
    }

    //本方法用于将数组中两个元素交换位置
    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    //本方法用于将传入字符串下标与符合要求的字符串下标包装为一个List并返回
    public static List<Integer> addRecord(int strIndex, int indexValid) {
        List<Integer> res = new ArrayList<>();
        res.add(strIndex);
        res.add(indexValid);
        return res;
    }

    //本方法用于获取处理后可以用于manacher算法的manacherChars
    public static char[] getManacherChars(char[] strChars) {
        char[] res = new char[strChars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            if ((i & 1) == 0) {
                res[i] = '#';
            } else {
                res[i] = strChars[index++];
            }
        }
        return res;
    }

    //本方法用于获取manacher字符串的pArr
    //manacher方法的改写
    public static int[] getManacherPArr(char[] manacherChar) {
        int c = -1;
        int pR = -1;
        int[] pArr = new int[manacherChar.length];

        for (int i = 0; i < pArr.length; i++) {
            //首先为pArr[i]赋一个初始值,为当前坐标距离pR距离与中心位置c的对称位置上的半径pArr[2 * c - i]
            pArr[i] = pR > i ? Math.min(pR - i, pArr[2 * c - i]) : 1;
            //基于这个初始值,看能不能继续扩
            //在不越界的前提下扩,条件中就表示左右边界的下一个位置
            while (i - pArr[i] > -1 && i + pArr[i] < pArr.length) {
                if (manacherChar[i - pArr[i]] == manacherChar[i + pArr[i]]) {
                    pArr[i]++;
                } else { //因为是回文,一旦有一个位置不符合,就直接break
                    break;
                }
            }
            //更新pR与c
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                c = i;
            }
        }
        return pArr;
    }

    public static void main(String[] args) {
//        String[] strings = new String[] {"bat","tab","cat"};
        String[] strings1 = new String[] {"abcd","dcba","||s","s","sss||"};
        List<List<Integer>> res = palindromePairs(strings1);
        System.out.println();
    }

}
