package com.newcoder.zuo3.advanced.class05;

public class Code_04_Decompress_String {
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

    public static class ReturnData {
        private String string;
        private int index;

        public ReturnData(String string, int index) {
            this.string = string;
            this.index = index;
        }
    }

    public static String decompress(String decompressStr) {
        char[] chars = decompressStr.toCharArray();
        return process(chars, 0).string;
    }

    public static ReturnData process(char[] chars, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        int times = 0;
        while (index < chars.length && chars[index] != '}') {
            if (chars[index] == '{') {
                ReturnData returnData = process(chars, index + 1);
                stringBuilder.append(getTimesString(times, returnData.string));
                times = 0;
                index = returnData.index + 1;
            } else {
                if (chars[index] >= '0' && chars[index] <= '9') {
                    times = times * 10 + chars[index] - '0';
                }
                if (chars[index] >= 'a' && chars[index] <= 'z') {
                    stringBuilder.append(String.valueOf(chars[index]));
                }
                index++;
            }
        }
        return new ReturnData(stringBuilder.toString(), index);
    }

    public static String getTimesString(int times, String base) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(base);
        }
        return stringBuilder.toString();
    }
    //for test

    //    public static String decompress(String decompressStr) {
//        char[] chs = decompressStr.toCharArray();
//        return process(chs, 0).str;
//    }
//
//    public static class ReturnData {
//        public String str;
//        public int end;
//
//        public ReturnData(String str, int nextIndex) {
//            this.str = str;
//            this.end = nextIndex;
//        }
//    }
//
//    public static ReturnData process(char[] chs, int index) {
//        StringBuilder res = new StringBuilder();
//        int times = 0;
//        while (index < chs.length && chs[index] != '}') {
//            if (chs[index] == '{') {
//                ReturnData returnData = process(chs, index + 1);
//                res.append(getTimesString(times, returnData.str));
//                times = 0;
//                index = returnData.end + 1;
//            } else {
//                if (chs[index] >= '0' && chs[index] <= '9') {
//                    times = times * 10 + chs[index] - '0';
//                }
//                if (chs[index] >= 'a' && chs[index] <= 'z') {
//                    res.append(String.valueOf(chs[index]));
//                }
//                index++;
//            }
//        }
//        return new ReturnData(res.toString(), index);
//    }
//
//    public static String getTimesString(int times, String base) {
//        StringBuilder res = new StringBuilder();
//        for (int i = 0; i < times; i++) {
//            res.append(base);
//        }
//        return res.toString();
//    }
    public static void main(String[] args) {
        String test1 = "3{a}2{bc}";
        String test2 = "3{a2{c}}";
        String test3 = "2{abc}3{cd}ef";
        System.out.println(decompress(test1));
        System.out.println(decompress(test2));
        System.out.println(decompress(test3));

    }


}
