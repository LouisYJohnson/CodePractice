package myCodePractice.zuo.advance.class05;


public class DecompressString {
    //某位程序想出了一个压缩字符串的方法， 压缩后的字符串如下:
    //3{a}2{bc}， 3{a2{c}}， 2{abc}3{cd}ef， 现在 需要你写一个解
    //压的程序， 还原原始的字符串。 如: s = "3{d}2{bc}", return
    //"dddbcbc". s = "3{a2{d}}", return "addaddadd". s =
    //"2{efg}3{cd}ef", return "efgefgcdcdcdef". 重复次数可以
    //确保是一个正整数。

    //练递归(子流程和父流程都是同一个逻辑的问题)
    //看见嵌套就往递归与栈方面想(递归与栈本质上都是一回事)
    //
    //遇到中括号{就进入递归,递归的功能是算出对应后面的中括号中间的部分变成字符串
    //base case:遇到}就停
    //
    //这道题同类型的还有括号求值
    //比较特殊的地方在于,base case不在递归函数的头部,而是在尾部

    //递归函数功能:
    //  给定字符串和开始下标,返回这个字符串解压后对应的字符串以及这个解压后的字符串的最后一个字符的
    public static class ReturnData {
        //returnString为递归函数返回的解压完毕的字符串
        private String returnString;
        //returnIndex为递归函数返回的解压完毕的字符串对应的原字符串中的}的下标位置
        private int returnIndex;

        public ReturnData(String returnString, int returnIndex) {
            this.returnString = returnString;
            this.returnIndex = returnIndex;
        }
    }

    public static String decompressString(String string) {
        if (string == null || string.length() == 0) return null;

        char[] string2Chars = string.toCharArray();
        return process(string2Chars, 0).returnString;
    }

    //递归函数功能:
    //  给定字符串和起始下标,返回起始下标开始的字符串解压后的字符串与该字符串解压后对应的最后一个'}'的坐标
    public static ReturnData process(char[] chars, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        int times = 0;

        //根据出现的字符是数字(算作出现多少次,也就是times)还是字符(有可能被压缩(在"{}"内),也有可能不参与压缩(在"{}"外))
        while (index < chars.length && chars[index] != '}') {
            if (chars[index] == '{') {
                ReturnData returnData = process(chars, index + 1);
                stringBuffer.append(getStringByTimes(returnData.returnString, times));
                //times用完后要清零
                times = 0;
                //index要更新为返回的数据中对应转化前压缩字符串的最后一个位置(即'}')的下一个
                index = returnData.returnIndex + 1;
            } else {
                //有可能单独出现了字符或者数字
                //  如果是数字:计入time中
                //  如果是字符:直接append到结果中即可
                if (chars[index] >= '0' && chars[index] <= '9') {
                    times = times * 10 + (chars[index] - '0');
                } else {
                    stringBuffer.append(chars[index]);
                }
                index++;
            }
        }
        //根据上面的while循环条件,如果进入子过程的话,index一定是在'}'的位置上停下的(在'}'位置时不满足条件跳出循环)
        //所以这里应该返回的信息为index而不是index+1(这符合递归函数返回信息的标准)
        return new ReturnData(stringBuffer.toString(), index);
    }

    //传入times与String返回重复times次的String
    public static String getStringByTimes(String string, int times) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < times; i++) {
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String test1 = "3{a}2{bc}";
        String test2 = "3{a2{c}}";
        String test3 = "2{abc}3{cd}ef";
        System.out.println(decompressString(test1));
        System.out.println(decompressString(test2));
        System.out.println(decompressString(test3));

    }
}
