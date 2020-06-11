package com.newcoder.zuo3.advanced.class05;

public class Code_05_ExpressionNumber {
    //表达式得到期望结果的组成种数
    //【题目】
    //给定一个只由0（假） 、 1(真)、 &(逻辑与)、 |（逻辑或） 和^(异或)五种字符组成的字符
    //串express， 再给定一个布尔值desired。 返回express能有多少种组合方式， 可以达到
    //desired的结果。
    //【举例】
    //express="1^0|0|1"， desired=false。
    //只有1^((0|0)|1)和1^(0|(0|1))的组合可以得到false， 返回2。
    //express="1"， desired=false。
    //无组合则可以得到false， 返回0。

    //解法:
    //式子中的东西都是固定顺序的,自由组合小括号,所以要先判断这个式子是否有效
    //然后写递归,这个递归的功能就是这个字符串里面(不用分左右,内部两个递归函数连续用(同样逻辑的子问题),就是分左右了)
    // 的为true和false的有多少种请你都求出来
    //式子中的东西都是固定顺序的,自由组合小括号,所以要先判断这个式子是否有效
    //式子必然以0或者1开头和结尾,并且偶数位上必须为0或者1,奇数位上必须为判断条件
    public static boolean isValid(char[] exp) {
        for (int i = 0; i < exp.length; i++) {
            if ((i & 1) == 1) { //如果是奇数,必须为判断条件
                if (exp[i] != '&' && exp[i] != '|' && exp[i] != '^') {
                    return false;
                }
            } else { //如果是偶数.必须为0或者1
                if (exp[i] != '0' && exp[i] != '1') {
                    return false;
                }
            }
        }
        return true;
    }

    //写递归,递归函数的功能是:
    //给一个字符串(字符数组中的索引开始与结束),返回这个字符串能够有多少种true和false的组合
    //因为返回的是两个结果而不是一个,所以给封装成一个类返回
    public static class ReturnData {
        private int trueNums;
        private int falseNums;

        public ReturnData(int trueNums, int falseNums) {
            this.trueNums = trueNums;
            this.falseNums = falseNums;
        }
    }

    //递归函数的功能是:
    //给一个字符串(字符数组中的索引开始与结束),返回这个字符串能够有多少种true和false的组合
    public static ReturnData process(char[] exp, int l, int r) {
        //base case
        if (l == r) {
            if (exp[l] == '0') {
                return new ReturnData(0, 1);
            } else {
                return new ReturnData(1, 0);
            }
        }
        //从头遍历字符数组的每一个逻辑符号位,并返回逻辑符号位左右的字符数组的true与false有多少种(递归)
        int trueNums = 0;
        int falseNums = 0;
        //假设左边为true的可能为a,false可能为b
        //右边true的可能为c,false可能为d
        //如果中间的逻辑判断字符是&与:
        //  ture:a*c
        //  false:a*d+b*c+b*d
        //如果中间的逻辑判断字符是|或:
        //  true:a*c+a*d+b*c
        //  false:b*d
        //如果中间的逻辑判断字符是^异或:
        //  true:a*d+b*c
        //  false:a*c+b*d
        for (int i = l + 1; i < r; i += 2) {   //每次都找到字符数组中的逻辑符号位(从l+1开始,每次跳两个)
            //因为字符数组在进入递归之前必然经过了valid检查,所以在这一步必定是能够跳到逻辑判断符号上的
            //左边为true的可能为a,false可能为b
            //右边true的可能为c,false可能为d
            //进入了process的子过程中,左右就是l和r而不是0和exp.length-1了
            ReturnData leftPart = process(exp, l, i - 1);
            ReturnData rightPart = process(exp, i + 1, r);
            int a = leftPart.trueNums;
            int b = leftPart.falseNums;
            int c = rightPart.trueNums;
            int d = rightPart.falseNums;
            if (exp[i] == '&') {    //且,&
                trueNums += a * c;
                falseNums += a * d + b * c + b * d;
            } else if (exp[i] == '|') {  //或,|
                trueNums += a * c + a * d + b * c;
                falseNums += b * d;
            } else {     //异或 ^
                trueNums += a * d + b * c;
                falseNums += a * c + b * d;
            }
        }
        return new ReturnData(trueNums, falseNums);
    }

    public static int num1(String express, boolean desire) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] chars = express.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        ReturnData returnData = process(chars, 0, chars.length - 1);
        if (desire) {
            return returnData.trueNums;
        }else {
            return returnData.falseNums;
        }
    }

    //暴力递归改动态规划:
    //这个改法有点复杂,绕不过来,可以举一个具体的例子来发现规律
    //水平方向:trueMap:a,falseMap:b
    //垂直方向:trueMap:c,falseMap:d
    //trueNums对应了trueMap
    //falseNums对应了falseMap
    public static int num2 (String str, boolean desire) {
        char[] chars = str.toCharArray();
        int[][] trueMap = new int[chars.length][chars.length];
        int[][] falseMap = new int[chars.length][chars.length];
        //先填基本位置
        for (int i = 0; i < trueMap.length; i += 2) {
            trueMap[i][i] = chars[i] == '1' ? 1 : 0;
            falseMap[i][i] = chars[i] == '0' ? 1 : 0;
        }
        //再填普遍位置,填的方法,根据具体例子(图拍下来了,可以去课件中找)来填值
        //这里的填法就是从l==r隔一个斜列填,并且每个数都需要它的下边与右边隔一个格子直到l==r为止
        //在填表的时候,发现每个普遍位置的依赖都是需要遍历填表的,也就是63-97行,照着搬就好了
        for (int i = chars.length - 3; i >= 0; i -= 2) {
            for (int j = i + 2; j < chars.length; j += 2) {
                //外面两层循环是找到每一个需要填东西的位置,最后一个for循环则是套用了63-97行的填东西的方法
                //l对应i,r对应j
                int trueNums = 0;
                int falseNums = 0;
                for (int k = i + 1; k < j; k += 2) {
                    int a = trueMap[i][k - 1];
                    int b = falseMap[i][k - 1];
                    int c = trueMap[k + 1][j];
                    int d = falseMap[k + 1][j];
                    if (chars[k] == '&') {    //且,&
                        trueNums += a * c;
                        falseNums += a * d + b * c + b * d;
                    } else if (chars[k] == '|') {  //或,|
                        trueNums += a * c + a * d + b * c;
                        falseNums += b * d;
                    } else {     //异或 ^
                        trueNums += a * d + b * c;
                        falseNums += a * c + b * d;
                    }
                }
                trueMap[i][j] = trueNums;
                falseMap[i][j] = falseNums;
            }
        }
        return desire ? trueMap[0][chars.length - 1] : falseMap[0][chars.length - 1];
    }

    //for test
    //动态规划by zuo
//    public static int num2(String express, boolean desired) {
//        if (express == null || express.equals("")) {
//            return 0;
//        }
//        char[] exp = express.toCharArray();
//        if (!isValid(exp)) {
//            return 0;
//        }
//        int[][] t = new int[exp.length][exp.length];
//        int[][] f = new int[exp.length][exp.length];
//        t[0][0] = exp[0] == '0' ? 0 : 1;
//        f[0][0] = exp[0] == '1' ? 0 : 1;
//        for (int i = 2; i < exp.length; i += 2) {
//            t[i][i] = exp[i] == '0' ? 0 : 1;
//            f[i][i] = exp[i] == '1' ? 0 : 1;
//            for (int j = i - 2; j >= 0; j -= 2) {
//                for (int k = j; k < i; k += 2) {
//                    if (exp[k + 1] == '&') {
//                        t[j][i] += t[j][k] * t[k + 2][i];
//                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
//                    } else if (exp[k + 1] == '|') {
//                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
//                        f[j][i] += f[j][k] * f[k + 2][i];
//                    } else {
//                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
//                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
//                    }
//                }
//            }
//        }
//        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
//    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(num1(express, desired));
        System.out.println(num2(express, desired));
    }
}
