package myCodePractice.zuo.basic.class07;

public class PrintAllSubsquences {
    //打印一个字符串的全部子序列， 包括空字符串
    //遇到这种子序列的问题,套路就是看前面的位置都排好了,当前位置怎么办
    public static void prinAllSubsequences (String str) {
        if (str == null) return;
        char[] chars = str.toCharArray();

    }

    //递归函数功能:
    //0-i-1位置上都已经选好了,i位置等待确定,并打印结果
    public static void process(char[] chars, int i) {
        //base case
        //如果已经到了尽头,就不用再选了,直接将得到的序列打印就行了
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }

        //决定当前位置i上的元素要还是不要
        //要:
        process(chars, i + 1);
        //不要
        char temp = chars[i];
        chars[i] = 0;
        process(chars, i + 1);
        //上面对一个引用类型的变量进行了修改,这种修改在递归调用中的影响是全局的
        //所以要在出了递归之后再改回来
        chars[i] = temp;
    }
}
