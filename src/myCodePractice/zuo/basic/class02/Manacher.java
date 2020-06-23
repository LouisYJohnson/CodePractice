package myCodePractice.zuo.basic.class02;

public class Manacher {
    //求一个字符串中的最长回文子串长度
    //在做Manacher算法之前,需要将字符串进行处理,每个字符的前面与后面都是一个特殊符号(随便选,这里选择'#')
    //Manacher算法需要3个东西(在字符串经过处理后):
    //  1.每个位置上的回文半径(包括当前点):用数组表示,pArr
    //  2.回文最右边界pR(实际上对应的是最右边界的下一个位置),初始值为-1
    //  3.当取得回文最右边界的中心c(回文最右边界不变,中心也不变),初始值为-1
    public static int getMaxLcpsLength(String str) {
        if (str == null || str.length() ==0) return 0;

        int res = Integer.MIN_VALUE;
        char[] strManacherString = getManacherChars(str);
        int c = -1;
        int pR = -1;
        int[] pArr = new int[strManacherString.length];

        for (int i = 0; i < pArr.length; i++) {
            //通过当前坐标i与pR之间的关系确定pArr[i]的初始值
            pArr[i] = pR > i ? Math.min(pR - i, pArr[2 * c - i]) : 1;
            //从初始值尝试是否能往外扩
            //这两个位置表示的是当前半径再往外一个的位置(判断的就是这两个在当前回文串外面的位置)
            //看这两个位置上的字符是否相等,如果相等,继续,不相等直接break
            while (i - pArr[i] >= 0 && i + pArr[i] <pArr.length) {
                if (strManacherString[i - pArr[i]] == strManacherString[i + pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }

            //接下来更新pR与对应的c
            if (i + pArr[i] > pR){
                pR = pArr[i];
                c = i;
            }
            //更新最大值
            res = Math.max(res, pArr[i]);
        }

        return res - 1;
    }

    //获取Manacher需要的Chars的形式
    public static char[] getManacherChars(String string) {
        char[] chars = string.toCharArray();
        char[] res = new char[string.length() * 2 + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            if ((i & 1) == 0) {
                res[i] = '#';
            }else {
                res[i] = chars[index++];
            }
        }
        return res;
    }

    //for test
    public static void main(String[] args) {
        String str1 = "abc1234321cbad";
        System.out.println(getMaxLcpsLength(str1));
    }
}
