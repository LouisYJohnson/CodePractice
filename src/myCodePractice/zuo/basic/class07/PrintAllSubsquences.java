package myCodePractice.zuo.basic.class07;

public class PrintAllSubsquences {
    //    打印一个字符串的全部子序列， 包括空字符串
    //流程:当前位置要或者不要,当前位置之前的已经确定 ,当前位置之后的还没有确定
    //大流程可以分解为两个小流程(当前位置要,不要),并且这些小流程与大流程逻辑完全一致
    public static void prinAllSubsequences(String str) {
        if (str == null) return;
        char[] chars = str.toCharArray();

    }

    //递归函数功能:
    //当前位置i,0~i-1都已经选好了,i~N-1位置上随便选,并打印
    public static void process(char[] chars, int i) {
        //base case
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }

        process(chars, i + 1);
        char temp = chars[i];
        chars[i] = 0;
        process(chars, i + 1);
        chars[i] = temp;
    }
}
